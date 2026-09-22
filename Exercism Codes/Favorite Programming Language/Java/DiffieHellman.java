import java.math.BigInteger;
import java.util.Random;

class DiffieHellman {

    BigInteger privateKey(BigInteger prime) {
        return BigInteger.valueOf(new Random().nextInt(1, prime.intValue()));
    }

    BigInteger publicKey(BigInteger primeA, BigInteger primeB, BigInteger privateKey) {
        return primeB.modPow(privateKey, primeA);
    }

    BigInteger secret(BigInteger prime, BigInteger publicKey, BigInteger privateKey) {
        return publicKey(prime, publicKey, privateKey);
    }

}