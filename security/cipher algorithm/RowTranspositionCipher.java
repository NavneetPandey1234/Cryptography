import java.util.Scanner;

public class RowTranspositionCipher {
    public static String encrypt(String plaintext, String key) {
        int[] keyArray = getKeyArray(key);
        int numCols = keyArray.length;
        int numRows = (int) Math.ceil((double) plaintext.length() / numCols);

        char[][] grid = new char[numRows][numCols];
        for (int i = 0; i < numRows * numCols; i++) {
            int row = i / numCols;
            int col = i % numCols;
            if (i < plaintext.length()) {
                grid[row][col] = plaintext.charAt(i);
            } else {
                grid[row][col] = 'X'; // Padding character
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int col : keyArray) {
            for (int row = 0; row < numRows; row++) {
                ciphertext.append(grid[row][col]);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        int[] keyArray = getKeyArray(key);
        int numCols = keyArray.length;
        int numRows = (int) Math.ceil((double) ciphertext.length() / numCols);

        char[][] grid = new char[numRows][numCols];
        int index = 0;
        for (int col : keyArray) {
            for (int row = 0; row < numRows; row++) {
                grid[row][col] = ciphertext.charAt(index++);
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < numRows * numCols; i++) {
            int row = i / numCols;
            int col = i % numCols;
            plaintext.append(grid[row][col]);
        }

        return plaintext.toString().replace("X", ""); // Remove padding characters
    }

    private static int[] getKeyArray(String key) {
        int[] keyArray = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyArray[i] = Character.getNumericValue(key.charAt(i)) - 1;
        }
        return keyArray;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase(); // Remove non-letters and convert to
                                                                                    // uppercase

        System.out.println("Enter the key (e.g., 4312):");
        String key = sc.nextLine();

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}
