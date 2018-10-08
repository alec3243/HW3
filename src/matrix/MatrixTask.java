package matrix;

import java.util.Random;

public class MatrixTask implements Runnable {
	private float[][] matrixA;
	private float[][] matrixB;
	private float[][] matrixC;
	private int row;
	private int col;

	MatrixTask(float[][] matrixA, float[][] matrixB, float[][] matrixC,
			int row, int col) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
		this.row = row;
		this.col = col;
	}

	@Override
	public void run() {
		// Calculate the value for a single cell in the product matrix C
		float sum = 0.0f;
		for (int i = 0; i < matrixB.length; i++) {
			sum += matrixA[row][i] * matrixB[i][col];
		}
		matrixC[row][col] = sum;
	}

	/**
	 * Generates a matrix of size rows x cols with each value being a random
	 * float value between 0.0 and 10.0
	 * 
	 * @param rows
	 *            Number of rows to generate
	 * @param cols
	 *            Number of columns to generate
	 * @return Randomly generated matrix with specified size
	 */
	public static float[][] generateMatrix(int rows, int cols) {
		float[][] matrix = new float[rows][cols];
		Random rand = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = rand.nextFloat() * 10.0f;
			}
		}
		return matrix;
	}

}
