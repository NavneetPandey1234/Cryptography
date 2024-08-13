import java.util.Scanner;

public class CaesarCipher {

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();

        for (char letter : plaintext.toCharArray()) {
            if (Character.isLetter(letter)) {
                char base = Character.isUpperCase(letter) ? 'A' : 'a';
                char encryptedLetter = (char) ((letter - base + shift) % 26 + base);
                ciphertext.append(encryptedLetter);
            } else {
                ciphertext.append(letter); // Non-letters are not encrypted
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift); // Decryption is the inverse of encryption
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine();

        System.out.println("Enter the shift value:");
        int shift = sc.nextInt();

        String encryptedText = encrypt(plaintext, shift);
        String decryptedText = decrypt(encryptedText, shift);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}
