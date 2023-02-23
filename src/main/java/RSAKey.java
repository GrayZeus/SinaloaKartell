import java.math.BigInteger;

public class RSAKey {
    public class Key {
        private final BigInteger part01;
        private final BigInteger part02;

        public Key(BigInteger part01, BigInteger part02) {
            this.part01 = part01;
            this.part02 = part02;
        }

        public BigInteger getPart01() {
            return part01;
        }

        public BigInteger getPart02() {
            return part02;
        }

        public String toString() {
            return "(" + part01 + "," + part02 + ")";
        }
    }
}
