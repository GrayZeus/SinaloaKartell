public interface IRSA {
    byte[] encrypt(String plainMessage, RSAKey key);

    String decrypt(byte[] cipher, RSAKey key);
}
