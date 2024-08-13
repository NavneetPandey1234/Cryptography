import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class practical_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text: ");
        String text = scanner.nextLine();

        System.out.println("Input (string) : " + text);
        System.out.println("Input (length) : " + text.length());

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println("MD5 (hex) : " + hashtext);
            System.out.println("MD5 (length) : " + hashtext.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}