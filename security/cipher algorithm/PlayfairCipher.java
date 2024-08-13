import java.util.Scanner;

public class PlayfairCipher {

    private static final int SIZE = 5; // Size of the Playfair matrix

    // Method to prepare the key and remove duplicate characters
    private static String prepareKey(String key) {
        StringBuilder preparedKey = new StringBuilder();
        boolean[] visited = new boolean[26]; // to check if a character is already used

        key = key.toUpperCase().replaceAll("[^A-Z]", ""); // Convert to uppercase and remove non-alphabet characters

        // Process each character of the key
        for (char c : key.toCharArray()) {
            if (!visited[c - 'A']) {
                visited[c - 'A'] = true;
                preparedKey.append(c);
            }
        }

        // Fill the rest of the matrix with remaining alphabet (excluding 'J')
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J')
                continue; // Skip 'J' (I and J are treated the same in Playfair cipher)
            if (!visited[c - 'A']) {
                preparedKey.append(c);
            }
        }

        return preparedKey.toString();
    }

    // Method to generate the Playfair matrix
    private static char[][] generatePlayfairMatrix(String key) {
        char[][] matrix = new char[SIZE][SIZE];
        key = prepareKey(key);

        int index = 0;

        // Fill the matrix with the characters from the key
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = key.charAt(index++);
            }
        }

        return matrix;
    }

    // Method to find coordinates of a character in the Playfair matrix
    private static void findCoordinates(char[][] matrix, char ch, int[] coords) {
        if (ch == 'J')
            ch = 'I'; // Treat 'J' as 'I'

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == ch) {
                    coords[0] = i;
                    coords[1] = j;
                    return;
                }
            }
        }
    }

    // Method to encrypt plaintext using the Playfair cipher
    public static String encrypt(String plaintext, String key) {
        char[][] matrix = generatePlayfairMatrix(key);
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", ""); // Convert to uppercase and remove non-alphabet
                                                                      // characters
        StringBuilder ciphertext = new StringBuilder();

        // Replace 'J' with 'I'
        plaintext = plaintext.replace('J', 'I');

        // Handle digraphs (pairs of letters)
        for (int i = 0; i < plaintext.length(); i += 2) {
            char first = plaintext.charAt(i);
            char second = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X'; // Pad with 'X' if last pair has
                                                                                        // single character

            if (first == second)
                second = 'X'; // If both letters are the same, insert 'X' between them

            int[] coords1 = new int[2];
            int[] coords2 = new int[2];

            findCoordinates(matrix, first, coords1);
            findCoordinates(matrix, second, coords2);

            // Same row
            if (coords1[0] == coords2[0]) {
                ciphertext.append(matrix[coords1[0]][(coords1[1] + 1) % SIZE]);
                ciphertext.append(matrix[coords2[0]][(coords2[1] + 1) % SIZE]);
            }
            // Same column
            else if (coords1[1] == coords2[1]) {
                ciphertext.append(matrix[(coords1[0] + 1) % SIZE][coords1[1]]);
                ciphertext.append(matrix[(coords2[0] + 1) % SIZE][coords2[1]]);
            }
            // Form a rectangle
            else {
                ciphertext.append(matrix[coords1[0]][coords2[1]]);
                ciphertext.append(matrix[coords2[0]][coords1[1]]);
            }
        }

        return ciphertext.toString();
    }

    // Method to decrypt ciphertext using the Playfair cipher
    public static String decrypt(String ciphertext, String key) {
        char[][] matrix = generatePlayfairMatrix(key);
        StringBuilder plaintext = new StringBuilder();

        // Handle digraphs (pairs of letters)
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char first = ciphertext.charAt(i);
            char second = ciphertext.charAt(i + 1);

            int[] coords1 = new int[2];
            int[] coords2 = new int[2];

            findCoordinates(matrix, first, coords1);
            findCoordinates(matrix, second, coords2);

            // Same row
            if (coords1[0] == coords2[0]) {
                plaintext.append(matrix[coords1[0]][(coords1[1] + SIZE - 1) % SIZE]);
                plaintext.append(matrix[coords2[0]][(coords2[1] + SIZE - 1) % SIZE]);
            }
            // Same column
            else if (coords1[1] == coords2[1]) {
                plaintext.append(matrix[(coords1[0] + SIZE - 1) % SIZE][coords1[1]]);
                plaintext.append(matrix[(coords2[0] + SIZE - 1) % SIZE][coords2[1]]);
            }
            // Form a rectangle
            else {
                plaintext.append(matrix[coords1[0]][coords2[1]]);
                plaintext.append(matrix[coords2[0]][coords1[1]]);
            }
        }

        return plaintext.toString();
    }

    // Main method to demonstrate the Playfair Cipher
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        System.out.println("Enter the key:");
        String key = sc.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        // Remove non-alphabet characters and replace 'J' with 'I'
        plaintext = plaintext.replaceAll("[^A-Z]", "");
        key = key.replaceAll("[^A-Z]", "");

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Key: " + key);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
