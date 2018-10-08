package election;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		PriorityQueue<ElectedOfficial> officials = new PriorityQueue<>();
		Rank rankThread = new Rank(officials);
		rankThread.start();
		final int N = 5;
		ElectedOfficial newOfficial;
		for (int i = 0; i < N; i++) {
			newOfficial = ElectedOfficial.createOfficial(rankThread);
			officials.add(newOfficial);
			newOfficial.start();
		}

	}

}
