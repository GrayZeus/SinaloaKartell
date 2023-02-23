import java.util.Arrays;

public class RSAExample {
    public static void main(String[] args) {
        String message = "Test123Drogen";
        System.out.println(message);

        RSA rsa = new RSA();
        RSAKey[] rsaKeys = rsa.generateKeys(128);
        RSAKey publicKey = rsaKeys[0];
        RSAKey privateKey = rsaKeys[1];

        byte[] cipher = rsa.encrypt(message, publicKey);
        System.out.println(Arrays.toString(cipher));

        String encryptedText = rsa.decrypt(cipher, privateKey);
        System.out.println(encryptedText);
    }
}
