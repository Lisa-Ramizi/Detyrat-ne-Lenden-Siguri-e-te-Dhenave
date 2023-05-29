package rsa;

import java.util.Arrays;
import java.util.Random;

public class RandomMatrix {
    public static void main1(String[] args) {
        System.out.println("Matrix A");
        int[][] A = generateMatrix1(4, 4);

        System.out.println();
        
        int[][] matrix_1 = generateMatrix1(2, 2);
        if (findDeterminant(matrix_1) == 0) {
            matrix_1 = generateMatrix1(2, 2);
        }

        int[][] matrix_2 = generateMatrixWithFixedValues(matrix_1);
        if (findDeterminant(matrix_2) == 0) {
            matrix_2 = generateMatrixWithFixedValues(matrix_1);
        }

        System.out.println();
        System.out.println("Matrix MDS");
        int[][] MDS = generateMDS(matrix_1, matrix_2);
        if (findDeterminant(MDS) == 0) {
            MDS = generateMDS(matrix_1, matrix_2);
        }

        System.out.println();
        System.out.println();
        System.out.println("Matrix B = MDS * A");
        int[][] B = multiplyMatrices(MDS, A);
    }

    public static int[][] generateMatrix1(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            System.out.println();
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(256);
                System.out.printf("%-5d", matrix[i][j]);
            }
        }
        return matrix;
    }

    public static int[][] generateMatrixWithFixedValues(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        Random random = new Random();

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                if (i == 1 && j < 2) {
                    newMatrix[i][j] = matrix[0][j];
                } else if (i == 2 && j < 2) {
                    newMatrix[i][j] = matrix[1][j];
                } else {
                    newMatrix[i][j] = random.nextInt(256);
                }
                System.out.printf("%-5d", newMatrix[i][j]);
            }
        }
        return newMatrix;
    }

    public static int[][] generateMDS(int[][] matrix_1, int[][] matrix_2) {
        int[][] matrix_3 = new int[4][4];
        Random random = new Random();

        for (int i = 0; i < matrix_3.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix_3[i].length; j++) {
                if (i == 1 && j < 3) {
                    matrix_3[i][j] = matrix_2[0][j];
                } else if ((i == 2 || i == 3) && j < 2) {
                    matrix_3[i][j] = matrix_1[i - 2][j];
                } else {
                    matrix_3[i][j] = random.nextInt(256);
                }
                System.out.printf("%-5d", matrix_3[i][j]);
            }
        }
        return matrix_3;
    }

    public static int findDeterminant(int[][] matrix) {
        int n = matrix.length;
        int determinant = 0;

        if (n == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else if (n == 3) {
            determinant = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                    - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                    + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        } else if (n == 4) {
            determinant = matrix[0][0]
                    * (matrix[1][1] * (matrix[2][2] * matrix[3][3] - matrix[2][3] * matrix[3][2])
                            - matrix[1][2] * (matrix[2][1] * matrix[3][3] - matrix[2][3] * matrix[3][1])
                            + matrix[1][3] * (matrix[2][1] * matrix[3][2] - matrix[2][2] * matrix[3][1]))
                    - matrix[0][1]
                            * (matrix[1][0] * (matrix[2][2] * matrix[3][3] - matrix[2][3] * matrix[3][2])
                                    - matrix[1][2] * (matrix[2][0] * matrix[3][3] - matrix[2][3] * matrix[3][0])
                                    + matrix[1][3] * (matrix[2][0] * matrix[3][2] - matrix[2][2] * matrix[3][0]))
                    + matrix[0][2]
                            * (matrix[1][0] * (matrix[2][1] * matrix[3][3] - matrix[2][3] * matrix[3][1])
                                    - matrix[1][1] * (matrix[2][0] * matrix[3][1] - matrix[2][3] * matrix[3][0])
                                    + matrix[1][3] * (matrix[2][0] * matrix[3][1] - matrix[2][1] * matrix[3][0]))
                    - matrix[0][3]
                            * (matrix[1][0] * (matrix[2][1] * matrix[3][2] - matrix[2][2] * matrix[3][1])
                                    - matrix[1][1] * (matrix[2][0] * matrix[3][2] - matrix[2][2] * matrix[3][0])
                                    + matrix[1][2] * (matrix[2][0] * matrix[3][1] - matrix[2][1] * matrix[3][0]));
        }
        return determinant;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int m = a.length;
        int n = b[0].length;
        int[][] c = new int[m][n];

        for (int i = 0; i < m; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < a[0].length; k++) {
                    int product = a[i][k] * b[k][j];
                    sum += product;
                }
                c[i][j] = sum;
                System.out.printf("%-5d", c[i][j]);
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println("Matrix A");

        int[][] A = generateMatrix1(4, 4);

        System.out.println();

        int[][] matrix1 = generateMatrix1(2, 2);
        if (findDeterminant(matrix1) == 0) {
            matrix1 = generateMatrix1(2, 2);
        }

        int[][] matrix2 = generateMatrix1(3, 3);
        matrix2[1][0] = matrix1[0][0];
        matrix2[1][1] = matrix1[0][1];
        matrix2[2][0] = matrix1[1][0];
        matrix2[2][1] = matrix1[1][1];
        if (findDeterminant(matrix2) == 0) {
            matrix2 = generateMatrix1(3, 3);
            matrix2[1][0] = matrix1[0][0];
            matrix2[1][1] = matrix1[0][1];
            matrix2[2][0] = matrix1[1][0];
            matrix2[2][1] = matrix1[1][1];
        }

        System.out.println();
        System.out.println("Matrix MDS");

        int[][] MDS = generateMatrix1(4, 4);
        MDS[2][0] = matrix1[0][0];
        MDS[2][1] = matrix1[0][1];
        MDS[3][0] = matrix1[1][0];
        MDS[3][1] = matrix1[1][1];
        MDS[1][0] = matrix2[0][0];
        MDS[1][1] = matrix2[0][1];
        MDS[1][2] = matrix2[0][2];
        MDS[2][2] = matrix2[1][2];
        MDS[3][2] = matrix2[2][2];
        if (findDeterminant(MDS) == 0) {
            MDS = generateMatrix1(4, 4);
            MDS[2][0] = matrix1[0][0];
            MDS[2][1] = matrix1[0][1];
            MDS[3][0] = matrix1[1][0];
            MDS[3][1] = matrix1[1][1];
            MDS[1][0] = matrix2[0][0];
            MDS[1][1] = matrix2[0][1];
            MDS[1][2] = matrix2[0][2];
            MDS[2][2] = matrix2[1][2];
            MDS[3][2] = matrix2[2][2];
        }

        System.out.println();
        System.out.println();
        System.out.println("Matrix B = MDS * A");

        int[][] B = multiplyMatrices(A, MDS);
    }

    public static int[][] generateMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            System.out.println();
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (Math.random() * 256);
                System.out.printf("%-5d", matrix[i][j]);
            }
        }
        return matrix;
    }
}
