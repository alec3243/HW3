package matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static final int THREADS = 6;

	public static void main(String[] args) {
		final int ROWS_A = 3000;
		final int COL_A = 3000;
		final int ROWS_B = 3000;
		final int COL_B = 3000;
		float[][] matrixA = MatrixTask.generateMatrix(ROWS_A, COL_B);
		float[][] matrixB = MatrixTask.generateMatrix(ROWS_B, COL_B);
		float[][] matrixC = new float[ROWS_A][COL_B];
		long start = System.currentTimeMillis();
		matMult(matrixA, matrixB, matrixC, ROWS_A, COL_A, COL_B);
		long end = System.currentTimeMillis();

	}

	private static void matMult(float[][] A, float[][] B, float[][] C, int m,
			int n, int p) {

		// 2 THREADS
		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		MatrixTask task = null;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < p; j++) {
				task = new MatrixTask(A, B, C, i, j);
				executor.execute(task);
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.yield();
		}
		long end = System.currentTimeMillis();
		System.out.printf("Time with %d thread(s): %d%n", 2, end - start);

		// 4 THREADS
		start = System.currentTimeMillis();
		executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < p; j++) {
				task = new MatrixTask(A, B, C, i, j);
				executor.execute(task);
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.yield();
		}
		end = System.currentTimeMillis();
		System.out.printf("Time with %d thread(s): %d%n", 4, end - start);

		// 6 THREADS
		start = System.currentTimeMillis();
		executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < p; j++) {
				task = new MatrixTask(A, B, C, i, j);
				executor.execute(task);
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.yield();
		}
		end = System.currentTimeMillis();
		System.out.printf("Time with %d thread(s): %d%n", 6, end - start);

		// 8 THREADS
		start = System.currentTimeMillis();
		executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < p; j++) {
				task = new MatrixTask(A, B, C, i, j);
				executor.execute(task);
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.yield();
		}
		end = System.currentTimeMillis();
		System.out.printf("Time with %d thread(s): %d%n", 8, end - start);
	}

}
