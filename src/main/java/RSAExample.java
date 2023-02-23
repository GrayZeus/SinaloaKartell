import java.math.BigInteger;
import java.util.Arrays;

public class RSAExample {
    public static void main(String[] args) {
        String message = "TESTDROGEN";
        System.out.println(message);

        RSA rsa = new RSA();
        RSAKey[] rsaKeys = rsa.generateKeys(18);
        RSAKey publicKey = rsaKeys[0];
        RSAKey privateKey = rsaKeys[1];

        BigInteger[] cipher = rsa.encrypt(message, publicKey);
        System.out.println(Arrays.toString(cipher));

        String decryptedText = rsa.decrypt(cipher, privateKey);
        System.out.println(decryptedText);
    }
}
