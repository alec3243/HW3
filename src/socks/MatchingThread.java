package socks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
		CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
		for (int i = 0; i < THREAD_COUNT; i++) {
			(new SockThread(this, latch)).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		providePair();
	}

	private void providePair() {
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
