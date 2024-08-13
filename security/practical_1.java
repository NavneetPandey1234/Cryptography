import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

public class practical_1 {

    public static void main(String[] args) throws Exception {
        // Initialize the key pair generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        // Generate the key pair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);

        // Get the user input
        System.out.println("Enter the message to be signed: ");
        String message = scanner.nextLine();

        // Initialize the signature object
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes("UTF-8"));

        // Get the digital signature
        byte[] digitalSignature = signature.sign();

        // Initialize the signature object for verification
        Signature verifySignature = Signature.getInstance("SHA1withRSA");
        verifySignature.initVerify(publicKey);
        verifySignature.update(message.getBytes("UTF-8"));

        // Verify the digital signature
        boolean isVerified = verifySignature.verify(digitalSignature);

        // Print the result
        System.out.println("Digital signature verified: " + isVerified);
    }
}