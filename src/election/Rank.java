package election;

import java.util.PriorityQueue;

public class Rank extends Thread {
	private PriorityQueue<ElectedOfficial> officials;
	private ElectedOfficial highestRank;

	Rank(PriorityQueue<ElectedOfficial> officials) {
		this.officials = officials;
		// Establish the highest rank at -infinity
		highestRank = new ElectedOfficial(Integer.MIN_VALUE, this);
	}

	@Override
	public void run() {
		while (true) {
			// A new ElectedOfficial thread has started
			if (Thread.interrupted()) {
				if (!highestRank.equals(officials.peek())) {
					// There is a new highest ranking official
					highestRank = officials.peek();
					// Interrupt all ElectedOfficial threads
					officials.forEach(official -> {
						official.setLeaderName(highestRank.getName());
						official.interrupt();
					});
					break;
				}
			}
			Thread.yield();
		}
	}
}
