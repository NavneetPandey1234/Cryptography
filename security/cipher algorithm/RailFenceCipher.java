import java.util.Scanner;

public class RailFenceCipher {

    // Encrypts a given plaintext using the Rail Fence cipher
    public static String encrypt(String plaintext, int rails) {
        char[][] matrix = new char[rails][plaintext.length()];
        int row = 0;
        boolean down = false;

        // Fill the matrix with placeholders
        for (int i = 0; i < plaintext.length(); i++) {
            if (row == 0 || row == rails - 1) {
                down = !down;
            }
            matrix[row][i] = plaintext.charAt(i);
            row += down ? 1 : -1;
        }

        // Read cipher text row by row
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                if (matrix[i][j] != '\u0000') {
                    ciphertext.append(matrix[i][j]);
                }
            }
        }

        return ciphertext.toString();
    }

    // Decrypts a given ciphertext using the Rail Fence cipher
    public static String decrypt(String ciphertext, int rails) {
        char[][] matrix = new char[rails][ciphertext.length()];
        int row = 0;
        boolean down = false;

        // Fill the matrix with placeholders to simulate rail pattern
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0) {
                down = true;
            } else if (row == rails - 1) {
                down = false;
            }
            matrix[row][i] = '*'; // Placeholder for filled positions
            row += down ? 1 : -1;
        }

        // Fill the matrix with the ciphertext
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                if (matrix[i][j] == '*' && index < ciphertext.length()) {
                    matrix[i][j] = ciphertext.charAt(index++);
                }
            }
        }

        // Read plaintext column by column
        StringBuilder plaintext = new StringBuilder();
        row = 0;
        down = false;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0 || row == rails - 1) {
                down = !down;
            }
            plaintext.append(matrix[row][i]);
            row += down ? 1 : -1;
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine().toUpperCase(); // Read plaintext input

        System.out.println("Enter the number of rails:");
        int rails = sc.nextInt(); // Read number of rails

        String encryptedText = encrypt(plaintext, rails);
        String decryptedText = decrypt(encryptedText, rails);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
