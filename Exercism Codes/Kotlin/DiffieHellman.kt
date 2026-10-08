import java.math.BigInteger
import java.util.Random

object DiffieHellman {

    fun privateKey(prime: BigInteger): BigInteger {
        return BigInteger.valueOf(Random().nextInt(1, prime.intValueExact()).toLong())
    }

    fun publicKey(p: BigInteger, g: BigInteger, privKey: BigInteger): BigInteger {
        return g.modPow(privKey, p)
    }

    fun secret(prime: BigInteger, publicKey: BigInteger, privateKey: BigInteger): BigInteger {
        return publicKey(prime, publicKey, privateKey)
    }
}
