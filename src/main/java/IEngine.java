public interface IEngine {
    String generateKey(String plainMessage, StringBuilder keyword);

    String encrypt(String plainMessage, String keyword);

    String decrypt(String cipher, String keyword);
}
