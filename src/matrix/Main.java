package matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		// Establish matrix dimensions
		final int ROWS_A = 1000;
		final int COL_A = 1000;
		final int ROWS_B = 1000;
		final int COL_B = 1000;
		System.out.println("Matrix size: 1000x1000");
		// Establish random matrices
		float[][] matrixA = MatrixTask.generateMatrix(ROWS_A, COL_B);
		float[][] matrixB = MatrixTask.generateMatrix(ROWS_B, COL_B);
		float[][] matrixC = new float[ROWS_A][COL_B];
		matMult(matrixA, matrixB, matrixC, ROWS_A, COL_A, COL_B);

	}

	/**
	 * Multiples matrices A and B together, storing the product in matrix C
	 * using varying amounts of threads
	 * 
	 * @param A
	 *            Matrix to be multiplied by B
	 * @param B
	 *            Matrix to multiply A
	 * @param C
	 *            Product matrix
	 * @param m
	 *            Rows in A
	 * @param n
	 *            Columns in A and rows in B
	 * @param p
	 *            Columns in B
	 */
	private static void matMult(float[][] A, float[][] B, float[][] C, int m,
			int n, int p) {
		int[] threads = { 1, 2, 4, 8 };
		// Multiply the matrices using 1, 2, 4, and 8 threads at a time
		for (int threadCount : threads) {
			long start = System.currentTimeMillis();
			ExecutorService executor = Executors
					.newFixedThreadPool(threadCount);
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
			System.out.printf("Time with %d thread(s): %dms%n", threadCount,
					end - start);

		}

	}

}
