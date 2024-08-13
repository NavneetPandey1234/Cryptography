import java.security.*;
import java.util.Base64;

public class RSADigitalSignatureExample {
    public static void main(String[] args) {
        try {
            // Generate key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // You can adjust the key size as needed
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Get private and public keys
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Create signature
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            String data = "Hello, world!";
            signature.update(data.getBytes());
            byte[] digitalSignature = signature.sign();

            // Verify signature
            Signature verifier = Signature.getInstance("SHA256withRSA");
            verifier.initVerify(publicKey);
            verifier.update(data.getBytes());
            boolean verified = verifier.verify(digitalSignature);

            System.out.println("Data: " + data);
            System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));
            System.out.println("Signature verified: " + verified);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }
}
