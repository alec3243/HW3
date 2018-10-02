package socks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class SockThread extends Thread {
	private List<Sock> socks;
	private MatchingThread parent;
	private CountDownLatch latch;

	public SockThread(MatchingThread parent, CountDownLatch latch) {
		this.parent = parent;
		this.latch = latch;
	}

	@Override
	public void run() {
		socks = new ArrayList<>();
		int sockCount = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		SockColor[] colors = SockColor.values();
		SockColor color = null;
		int reds = 0;
		int blues = 0;
		int greens = 0;
		int oranges = 0;
		for (int i = 0; i < sockCount; i++) {
			color = colors[ThreadLocalRandom.current().nextInt(0, 3 + 1)];
			switch (color) {
			case RED:
				reds++;
				break;
			case BLUE:
				blues++;
				break;
			case GREEN:
				greens++;
				break;
			case ORANGE:
				oranges++;
				break;
			}
			socks.add(new Sock(color));
		}

		System.out.printf("SockThread-%s produced %d red socks%n", Thread
				.currentThread().getName().charAt(7), reds);
		System.out.printf("SockThread-%s produced %d blue socks%n", Thread
				.currentThread().getName().charAt(7), blues);
		System.out.printf("SockThread-%s produced %d green socks%n", Thread
				.currentThread().getName().charAt(7), greens);
		System.out.printf("SockThread-%s produced %d orange socks%n", Thread
				.currentThread().getName().charAt(7), oranges);
		parent.accept(socks);

		latch.countDown();
	}
}
