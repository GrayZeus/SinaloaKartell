import java.math.BigInteger;

public record RSAKey(BigInteger part01, BigInteger part02) {
    public String toString() {
        return "(" + part01 + "," + part02 + ")";
    }
}