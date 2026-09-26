object Sieve {

    fun primesUpTo(upperBound: Int): List<Int> {
        val prime = MutableList(upperBound + 1) { true }
        var p = 2
        val result = mutableListOf<Int>()
        while (p * p <= upperBound) {
            if (prime[p]) {
                for (i in (p * p)..upperBound step p) {
                    prime[i] = false
                }
            }
            p++
        }
        for (i in 2..upperBound) {
            if (prime[i]) {
                result.add(i)
            }
        }
        return result
    }
}
