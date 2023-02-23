import java.math.BigInteger;

public interface IRSA {
    BigInteger[] encrypt(String plainMessage, RSAKey key);

    String decrypt(BigInteger[] cipher, RSAKey key);
    RSAKey[] generateKeys(int keySize);
}
