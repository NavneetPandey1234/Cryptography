import java.util.Scanner;

public class VigenereCipher {

    // Encrypts a given plaintext using the Vigenère cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase(); // Convert plaintext to uppercase
        key = key.toUpperCase(); // Convert key to uppercase

        // Iterate through each character in the plaintext
        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);

            // Encrypt only alphabetic characters
            if (Character.isLetter(c)) {
                // Calculate shift amount based on current key character
                int shift = key.charAt(j) - 'A';
                char encryptedChar = (char) ('A' + (c - 'A' + shift) % 26);
                ciphertext.append(encryptedChar);

                // Move to the next character in the key
                j = (j + 1) % key.length();
            } else {
                // Non-alphabetic characters remain unchanged
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    // Decrypts a given ciphertext using the Vigenère cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase(); // Convert ciphertext to uppercase
        key = key.toUpperCase(); // Convert key to uppercase

        // Iterate through each character in the ciphertext
        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);

            // Decrypt only alphabetic characters
            if (Character.isLetter(c)) {
                // Calculate shift amount based on current key character
                int shift = key.charAt(j) - 'A';
                char decryptedChar = (char) ('A' + (c - 'A' - shift + 26) % 26);
                plaintext.append(decryptedChar);

                // Move to the next character in the key
                j = (j + 1) % key.length();
            } else {
                // Non-alphabetic characters remain unchanged
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine(); // Read plaintext input

        System.out.println("Enter the keyword:");
        String key = sc.nextLine(); // Read keyword input

        // Remove non-alphabetic characters and convert to uppercase
        plaintext = plaintext.replaceAll("[^A-Za-z]", "").toUpperCase();
        key = key.replaceAll("[^A-Za-z]", "").toUpperCase();

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Keyword: " + key);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
