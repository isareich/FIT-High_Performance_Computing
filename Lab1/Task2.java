package Lab1;

import java.io.*;
import java.util.*;

public class Task2 {
    public static int[][] readMatrix(String fileName, String matrixName, int numRows, int numCols) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            boolean foundMatrix = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(matrixName)) {
                    foundMatrix = true;
                    break;
                }
            }
            if (!foundMatrix) {
                System.out.println("Matrix not found: " + matrixName);
                return null;
            }

            int[][] matrix = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                String[] values = scanner.nextLine().split("\t");
                for (int j = 0; j < numCols; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
            return matrix;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
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
        int mistakes = 0;
        int[][] matrixA = readMatrix("file1.txt", "Matrix A:", n, m);
        int[][] matrixB = readMatrix("file1.txt", "Matrix B:", m, k);
        int[][] finalMatrix1 = readMatrix("file1.txt", "Final Matrix:", n, k);

        long startTime = System.currentTimeMillis();
        if (matrixA != null && matrixB != null) {
            int[][] finalMatrix2 = Task1.multiplyMatrices(matrixA, matrixB);
            for(int i = 0; i < finalMatrix1.length; i ++){
                for(int j = 0; j < finalMatrix1[0].length; j++){
                    if (finalMatrix1[i][j] != finalMatrix2[i][j]) {
                        finalMatrix2[i][j] = -1;
                        mistakes += 1;
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            Task1.printMatrix(finalMatrix2);
            System.out.println("\nЧас виконання: " + executionTime + " мілісекунд");
            System.out.println("\nКількість помилок: " + mistakes);
        }
        else {
            System.out.println("Error reading matrices.");
        }
    }
}
