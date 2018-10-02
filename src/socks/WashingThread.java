package socks;

public class WashingThread extends Thread {

	@Override
	public void run() {
		Thread matcher = new MatchingThread(this);
		matcher.start();
	}

	public void accept(Sock sock1, Sock sock2) {
		System.out.printf("Washer Thread: Destroyed %s socks%n",
				sock1.getColor());
	}
}
