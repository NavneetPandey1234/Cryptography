// import java.util.Scanner;

// public class Affine_Cipher {
//     private static final int a = 5;
//     private static final int b = 7;
//     private static final int m = 26;

//     public static String encrypt(String plaintext) {
//         StringBuilder ciphertext = new StringBuilder();

//         for (char letter : plaintext.toCharArray()) {
//             if (Character.isLetter(letter)) {
//                 char encryptedLetter = (char) (((a * (letter - 'A') + b) % m) + 'A');
//                 ciphertext.append(encryptedLetter);
//             } else {
//                 ciphertext.append(letter);
//             }
//         }

//         return ciphertext.toString();
//     }

//     public static String decrypt(String ciphertext) {
//         StringBuilder plaintext = new StringBuilder();

//         int aInverse = findInverse(a);

//         for (char letter : ciphertext.toCharArray()) {
//             if (Character.isLetter(letter)) {
//                 char decryptedLetter = (char) (((aInverse * (letter - 'A' - b + m)) % m) + 'A');
//                 plaintext.append(decryptedLetter);
//             } else {
//                 plaintext.append(letter);
//             }
//         }

//         return plaintext.toString();
//     }

//     private static int findInverse(int a) {
//         for (int x = 1; x < m; x++) {
//             if ((a * x) % m == 1) {
//                 return x;
//             }
//         }
//         return -1;
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.println("Enter the plain text :");
//         String plaintext = sc.nextLine();
//         String encryptedText = encrypt(plaintext);
//         String decryptedText = decrypt(encryptedText);

//         System.out.println("Plaintext: " + plaintext);
//         System.out.println("Encrypted: " + encryptedText);
//         System.out.println("Decrypted: " + decryptedText);
//     }
// }

import java.util.Scanner;

public class AffineCipher {

    // Encrypts a given plaintext using the Affine cipher
    public static String encrypt(String plaintext, int a, int b, int m) {
        StringBuilder ciphertext = new StringBuilder();

        for (char letter : plaintext.toCharArray()) {
            if (Character.isLetter(letter)) {
                char encryptedLetter = (char) (((a * (letter - 'A') + b) % m) + 'A');
                ciphertext.append(encryptedLetter);
            } else {
                ciphertext.append(letter);
            }
        }

        return ciphertext.toString();
    }

    // Decrypts a given ciphertext using the Affine cipher
    public static String decrypt(String ciphertext, int a, int b, int m) {
        StringBuilder plaintext = new StringBuilder();

        int aInverse = findInverse(a, m); // Find modular multiplicative inverse of a

        for (char letter : ciphertext.toCharArray()) {
            if (Character.isLetter(letter)) {
                char decryptedLetter = (char) (aInverse * (letter - 'A' - b + m) % m + 'A');
                plaintext.append(decryptedLetter);
            } else {
                plaintext.append(letter);
            }
        }

        return plaintext.toString();
    }

    // Finds the modular multiplicative inverse of 'a' under modulo 'm'
    private static int findInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // If inverse does not exist
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine().toUpperCase(); // Convert plaintext to uppercase

        System.out.println("Enter the value of 'a' (1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25):");
        int a = sc.nextInt();

        System.out.println("Enter the value of 'b':");
        int b = sc.nextInt();

        // Validate 'a' to ensure it is coprime with 26
        int m = 26; // Size of the alphabet
        if (gcd(a, m) != 1) {
            System.out.println("Error: 'a' must be coprime with 26.");
            return;
        }

        String encryptedText = encrypt(plaintext, a, b, m);
        String decryptedText = decrypt(encryptedText, a, b, m);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    // Utility method to find the greatest common divisor (GCD) of two numbers
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
