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
    public byte[] encrypt(String plainMessage, RSAKey key) {
        byte[] bytes = plainMessage.getBytes(Charset.defaultCharset());
        return internalEncrypt(new BigInteger(bytes), key).toByteArray();
    }

    @Override
    public String decrypt(byte[] cipher, RSAKey key) {
        byte[] msg = internalEncrypt(new BigInteger(cipher), key).toByteArray();
        return new String(msg);
    }

    private BigInteger internalEncrypt(BigInteger message, RSAKey key) {
        return message.modPow(key.part02(), key.part01());
    }

    @Override
    public RSAKey[] generateKeys(int keySize) {
        if (keySize < 128) {
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
