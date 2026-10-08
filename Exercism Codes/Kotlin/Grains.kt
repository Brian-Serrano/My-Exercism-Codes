import java.math.BigInteger

object Board {

    fun getGrainCountForSquare(number: Int): BigInteger {
        if (number !in 1..64) {
            throw IllegalArgumentException()
        }
        return BigInteger.ONE.shiftLeft(number - 1)
    }

    fun getTotalGrainCount(): BigInteger {
        return getGrainCountForSquare(64).shiftLeft(1).subtract(BigInteger.ONE)
    }
}