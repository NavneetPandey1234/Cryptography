import java.util.Scanner;

public class HillCipher {

    private static final int MODULO = 26; // Modulo operation for alphabet size

    // Method to convert a character to its corresponding index ('A' -> 0, 'B' -> 1,
    // ..., 'Z' -> 25)
    private static int charToIndex(char ch) {
        return ch - 'A';
    }

    // Method to convert an index to its corresponding character (0 -> 'A', 1 ->
    // 'B', ..., 25 -> 'Z')
    private static char indexToChar(int index) {
        return (char) (index + 'A');
    }

    // Method to perform matrix multiplication (mod 26)
    private static int[][] matrixMultiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] = (result[i][j] + matrix1[i][k] * matrix2[k][j]) % MODULO;
                }
            }
        }
        return result;
    }

    // Method to calculate the modular multiplicative inverse of a number 'a' under
    // modulo 'm'
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // Method to calculate the determinant of a 2x2 matrix
    private static int determinant(int[][] matrix) {
        return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % MODULO;
    }

    // Method to calculate the inverse of a 2x2 matrix
    private static int[][] inverseMatrix(int[][] matrix) {
        int det = determinant(matrix);
        int inverseDet = modInverse(det, MODULO);

        // Calculate the adjugate of the matrix
        int[][] adjugate = {
                { matrix[1][1], -matrix[0][1] },
                { -matrix[1][0], matrix[0][0] }
        };

        // Calculate the inverse matrix
        int[][] inverse = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverse[i][j] = (adjugate[i][j] * inverseDet) % MODULO;
                if (inverse[i][j] < 0) {
                    inverse[i][j] += MODULO;
                }
            }
        }

        return inverse;
    }

    // Method to convert a string to its corresponding vector (array of integers)
    private static int[] stringToVector(String text) {
        text = text.replaceAll("[^A-Za-z]", "").toUpperCase();
        int[] vector = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            vector[i] = charToIndex(text.charAt(i));
        }
        return vector;
    }

    // Method to convert a vector (array of integers) to its corresponding string
    private static String vectorToString(int[] vector) {
        StringBuilder sb = new StringBuilder();
        for (int num : vector) {
            sb.append(indexToChar(num));
        }
        return sb.toString();
    }

    // Method to perform Hill cipher encryption
    public static String encrypt(String plaintext, int[][] keyMatrix) {
        int[] vector = stringToVector(plaintext);

        // Pad the vector if its length is odd (for a 2x2 matrix)
        if (vector.length % 2 != 0) {
            int[] paddedVector = new int[vector.length + 1];
            System.arraycopy(vector, 0, paddedVector, 0, vector.length);
            paddedVector[paddedVector.length - 1] = 0; // Pad with 'A'
            vector = paddedVector;
        }

        int[][] matrix = { { vector[0], vector[1] } };
        int[][] encryptedMatrix = matrixMultiply(matrix, keyMatrix);
        int[] encryptedVector = { encryptedMatrix[0][0], encryptedMatrix[0][1] };

        return vectorToString(encryptedVector);
    }

    // Method to perform Hill cipher decryption
    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int[] vector = stringToVector(ciphertext);
        int[][] inverseMatrix = inverseMatrix(keyMatrix);

        int[][] matrix = { { vector[0], vector[1] } };
        int[][] decryptedMatrix = matrixMultiply(matrix, inverseMatrix);
        int[] decryptedVector = { decryptedMatrix[0][0], decryptedMatrix[0][1] };

        return vectorToString(decryptedVector);
    }

    // Main method to demonstrate the Hill Cipher
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        System.out.println("Enter the key matrix (2x2):");
        int[][] keyMatrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = sc.nextInt() % MODULO; // Modulo operation to ensure values are within 0-25
            }
        }

        String encryptedText = encrypt(plaintext, keyMatrix);
        String decryptedText = decrypt(encryptedText, keyMatrix);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
