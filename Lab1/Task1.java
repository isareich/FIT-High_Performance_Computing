package Lab1;

import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Task1 {

    public static int[][] createMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
        return matrix;
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int n = matrixA.length;
        int m = matrixA[0].length;
        int k = matrixB[0].length;
        int[][] finalMatrix = new int[n][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int sum = 0;
                for (int p = 0; p < m; p++) {
                    sum += matrixA[i][p] * matrixB[p][j];
                }
                finalMatrix[i][j] = sum;
            }
        }
        return finalMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void writeMatrixFile(int[][]matrix, FileWriter writer )throws IOException{
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                writer.write(matrix[i][j] + "\t");
            }
            writer.write("\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть значення n");
        int n = scanner.nextInt();
        System.out.println("Введіть значення m");
        int m = scanner.nextInt();
        System.out.println("Введіть значення k");
        int k = scanner.nextInt();

        int[][] matrixA = createMatrix(n, m);
        int[][] matrixB = createMatrix(m, k);
        System.out.println("Матриця А: ");
        printMatrix(matrixA);
        System.out.println("\nМатриця B: ");
        printMatrix(matrixB);

        long startTime = System.currentTimeMillis();
        int[][] finalMatrix = multiplyMatrices(matrixA, matrixB);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("\nРезультат множення матриць: ");
        printMatrix(finalMatrix);
        System.out.println("\nЧас виконання: " + executionTime + " мілісекунд");

        try(FileWriter writer = new FileWriter("LAB1.txt", false)){
            writer.write("Matrix A:\n");
            writeMatrixFile(matrixA, writer);
            writer.write("Matrix B:\n");
            writeMatrixFile(matrixB, writer);
            writer.write("Final Matrix:\n");
            writeMatrixFile(finalMatrix, writer);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
