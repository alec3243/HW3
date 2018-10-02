package socks;

public class Main {

	public static void main(String[] args) {
		Thread washer = new WashingThread();
		washer.start();
	}
}
