// Клас для роботи з матрицями у Java
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            // Розміри матриць
            int rowsA = 3, colsA = 2, rowsB = 2, colsB = 3;

            // Ініціалізація матриць A та B
            long[][] matrixA = initializeMatrix(rowsA, colsA);
            long[][] matrixB = initializeMatrix(rowsB, colsB);

            // Виведення матриць A та B
            System.out.println("Matrix A:");
            printMatrix(matrixA);

            System.out.println("\nMatrix B:");
            printMatrix(matrixB);

            // Обчислення матричного добутку A × B
            long[][] matrixC = multiplyMatrices(matrixA, matrixB);

            System.out.println("\nMatrix C (Result of A × B):");
            printMatrix(matrixC);

            // Обчислення суми найбільших елементів парних рядків
            // та найменших елементів непарних рядків матриці C
            long result = calculateRowSum(matrixC);

            System.out.println("\nResult (Sum of max in even rows and min in odd rows): " + result);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Метод для ініціалізації матриці випадковими числами
    public static long[][] initializeMatrix(int rows, int cols) {
        long[][] matrix = new long[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100); // Значення в діапазоні 0-99
            }
        }
        return matrix;
    }

    // Метод для виведення матриці
    public static void printMatrix(long[][] matrix) {
        for (long[] row : matrix) {
            for (long element : row) {
                System.out.printf("%5d", element);
            }
            System.out.println();
        }
    }

    // Метод для обчислення матричного добутку
    public static long[][] multiplyMatrices(long[][] matrixA, long[][] matrixB) throws IllegalArgumentException {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Matrix dimensions do not allow multiplication.");
        }

        long[][] result = new long[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    // Метод для обчислення суми максимальних/мінімальних елементів
    public static long calculateRowSum(long[][] matrix) {
        long sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            long extreme = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                if (i % 2 == 0) { // Парний рядок
                    extreme = Math.max(extreme, matrix[i][j]);
                } else { // Непарний рядок
                    extreme = Math.min(extreme, matrix[i][j]);
                }
            }
            sum += extreme;
        }
        return sum;
    }
}
