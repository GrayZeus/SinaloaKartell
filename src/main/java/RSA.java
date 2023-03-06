import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RSA implements IRSA {
    private BigInteger n;
    private BigInteger phiN;
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;
    private BigInteger d;

    @Override
    public BigInteger[] encrypt(String plainMessage, RSAKey key) {
        byte[] bytes = plainMessage.getBytes(Charset.defaultCharset());
        BigInteger[] cipher = new BigInteger[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            cipher[i] = internalEncrypt(new BigInteger(String.valueOf(bytes[i])), key);
        }
        return cipher;
    }

    @Override
    public String decrypt(BigInteger[] cipher, RSAKey key) {
        byte[] message = new byte[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            message[i] = internalDecrypt(cipher[i], key);
        }
        return new String(message);
    }

    private BigInteger internalEncrypt(BigInteger messagePart, RSAKey key) {
        return messagePart.modPow(key.part02(), key.n());
    }

    private byte internalDecrypt(BigInteger cipherPart, RSAKey key) {
        return cipherPart.modPow(key.part02(), key.n()).toByteArray()[0];
    }

    @Override
    public RSAKey[] generateKeys(int keySize) {
        if (keySize < 8) {
            throw new IllegalArgumentException("key size too small");
        }

        SecureRandom randomGenerator = new SecureRandom();
        generatePQ(keySize / 2, randomGenerator);
        n = p.multiply(q);
        phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        generateExponents(invertibleSet());

        RSAKey[] keys = new RSAKey[2];
        keys[0] = new RSAKey(n, e);
        keys[1] = new RSAKey(n, d);
        return keys;
    }

    private void generatePQ(int bitLength, SecureRandom randomGenerator) {
        while (true) {
            p = generateOddPrime(bitLength, randomGenerator);
            q = generateOddPrime(bitLength, randomGenerator);
            if (!p.equals(q)) {
                return;
            }
        }
    }

    private BigInteger generateOddPrime(int bitLength, Random randomGenerator) {
        BigInteger two = new BigInteger("2");
        while (true) {
            BigInteger prime = BigInteger.probablePrime(bitLength, randomGenerator);
            if (!prime.mod(two).equals(BigInteger.ZERO)) {
                return prime;
            }
        }
    }

    private void generateExponents(BigInteger[] invertibleSet) {
        Random randomGenerator = new Random();
        while (true) {
            BigInteger invertible = invertibleSet[randomGenerator.nextInt(invertibleSet.length)];
            BigInteger inverse = invertible.modInverse(phiN);
            if (invertible.multiply(inverse).mod(phiN).equals(BigInteger.ONE.mod(phiN))) {
                e = invertible;
                d = inverse;
                return;
            }
        }
    }

    private BigInteger[] invertibleSet() {
        final int maxSize = 100000;
        Set<BigInteger> invertibleSet = new HashSet<>();
        BigInteger end = n.subtract(BigInteger.ONE);

        for (BigInteger i = new BigInteger("5"); i.compareTo(end) < 0; i = i.add(BigInteger.ONE)) {
            if (i.gcd(phiN).equals(BigInteger.ONE)) {
                invertibleSet.add(i);
                if (invertibleSet.size() == maxSize) {
                    break;
                }
            }
        }

        return invertibleSet.toArray(new BigInteger[0]);
    }
}
