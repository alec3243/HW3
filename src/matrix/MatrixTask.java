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
		float sum = 0.0f;
		for (int i = 0; i < matrixB.length; i++) {
			sum += matrixA[row][i] * matrixB[i][col];
		}
		matrixC[row][col] = sum;
		// for (int i = 1; i < matrixA.length; i++) {
		// for (int j = 1; j < matrixB[0].length; j++) {
		// float sum = 0.0f;
		// for (int k = 1; k < matrixA[0].length; k++) {
		// sum += matrixA[i][k] * matrixB[k][j];
		// }
		// matrixC[i][j] = sum;
		// }
		// }
	}

	public static float[][] generateMatrix(int rows, int cols) {
		float[][] matrix = new float[rows][cols];
		Random rand = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = rand.nextFloat() % 10.0f;
			}
		}
		return matrix;
	}

}
