import java.util.Scanner;

public class OneTimePadCipher {

    // Method to encrypt plaintext using the key
    public static String stringEncryption(String text, String key) {
        String cipherText = "";
        int[] cipher = new int[key.length()];

        // Generate cipher array based on plaintext and key
        for (int i = 0; i < key.length(); i++) {
            cipher[i] = text.charAt(i) - 'A' + key.charAt(i) - 'A';
            if (cipher[i] > 25) {
                cipher[i] -= 26;
            }
        }

        // Convert cipher array to characters and form cipher text
        for (int i = 0; i < key.length(); i++) {
            int x = cipher[i] + 'A';
            cipherText += (char) x;
        }

        return cipherText;
    }

    // Method to decrypt ciphertext using the key
    public static String stringDecryption(String s, String key) {
        String plainText = "";
        int[] plain = new int[key.length()];

        // Generate plain array based on ciphertext and key
        for (int i = 0; i < key.length(); i++) {
            plain[i] = s.charAt(i) - 'A' - (key.charAt(i) - 'A');
            if (plain[i] < 0) {
                plain[i] += 26;
            }
        }

        // Convert plain array to characters and form plaintext
        for (int i = 0; i < key.length(); i++) {
            int x = plain[i] + 'A';
            plainText += (char) x;
        }

        return plainText;
    }

    // Main method to demonstrate the One-Time Pad Cipher
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input plaintext from user
        System.out.println("Enter the plaintext:");
        String plainText = sc.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        // Input key from user
        System.out.println("Enter the key:");
        String key = sc.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        // Check if key length matches plaintext length
        if (plainText.length() != key.length()) {
            System.out.println("Error: Key length must be equal to plaintext length.");
            return;
        }

        // Encrypt the plaintext using the key
        String encryptedText = stringEncryption(plainText, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the encrypted text using the key
        String decryptedText = stringDecryption(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
