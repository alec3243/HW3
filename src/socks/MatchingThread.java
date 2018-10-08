package socks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

public class MatchingThread extends Thread {

	private List<Sock> socks;
	private WashingThread parent;

	public MatchingThread(WashingThread parent) {
		this.parent = parent;
	}

	@Override
	public void run() {
		final int THREAD_COUNT = 4;
		socks = new ArrayList<>();
		Phaser phaser = new Phaser(THREAD_COUNT + 1);
		for (int i = 0; i < THREAD_COUNT; i++) {
			(new SockThread(this, phaser)).start();
		}
		phaser.arriveAndAwaitAdvance();
		providePairs();
	}

	private void providePairs() {
		Sock sock1 = socks.get(0);
		Sock sock2 = null;
		for (int i = 1; i < socks.size(); i++) {
			sock2 = socks.get(i);
			if (sock1.getColor() == socks.get(i).getColor()) {
				break;
			}
		}
		System.out
				.printf("Matching Thread: Send %s socks to WasherThread. Total socks %d.%n",
						sock1.getColor(), socks.size());
		parent.accept(sock1, sock2);
	}

	public synchronized void accept(List<Sock> socks) {
		this.socks.addAll(socks);
	}

}
