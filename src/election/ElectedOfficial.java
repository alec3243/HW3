package election;

import java.util.Random;

public class ElectedOfficial extends Thread implements
		Comparable<ElectedOfficial> {
	private int rank;
	private Rank rankThread;
	private String leaderName;

	public static ElectedOfficial createOfficial(Rank rankThread) {
		Random rand = new Random();
		return new ElectedOfficial(rand.nextInt(), rankThread);
	}

	ElectedOfficial(int rank, Rank rankThread) {
		setRank(rank);
		setRankThread(rankThread);
		leaderName = Thread.currentThread().getName();
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setRankThread(Rank rankThread) {
		this.rankThread = rankThread;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	@Override
	public void run() {
		System.out
				.printf("Name: %s%nRank: %d%nThis thread thinks they're the leader.%n%n",
						Thread.currentThread().getName(), rank);
		rankThread.interrupt();
		while (true) {
			if (Thread.interrupted()) {
				System.out.printf("There is a new leader! Their name is %s%n",
						leaderName);
				break;
			}
		}
	}

	@Override
	public int compareTo(ElectedOfficial arg0) {
		if (this.getRank() > arg0.getRank()) {
			return -1;
		} else if (this.getRank() < arg0.getRank()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		ElectedOfficial official = (ElectedOfficial) obj;
		return rank == official.getRank()
				&& leaderName.equals(official.getLeaderName());
	}

}
