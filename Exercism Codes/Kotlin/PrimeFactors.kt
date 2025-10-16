object PrimeFactorCalculator {

    fun primeFactors(int: Int): List<Int> {
        return primeFactors(int.toLong()).map { it.toInt() }
    }

    fun primeFactors(long: Long): List<Long> {
        val result = mutableListOf<Long>()
        var num = long
        while (num > 1) {
            for (x in 2..num) {
                if (num % x == 0L) {
                    result.add(x)
                    num /= x
                    break
                }
            }
        }

        return result
    }
}
