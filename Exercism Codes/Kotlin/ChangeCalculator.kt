class ChangeCalculator(val coins: List<Int>) {

    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
        if (grandTotal == 0) {
            return emptyList()
        }
        if (grandTotal < 0) {
            throw IllegalArgumentException("Negative totals are not allowed.")
        }
        var possibleCoins = coins.map { listOf(it) }
        while (possibleCoins.none { it.sum() == grandTotal }) {
            possibleCoins = possibleCoins.flatMap { compute(it) }.distinct()

            if (possibleCoins.all { it.sum() > grandTotal }) {
                throw IllegalArgumentException("The total $grandTotal cannot be represented in the given currency.")
            }
        }
        return possibleCoins.first { it.sum() == grandTotal }
    }

    fun compute(coin: List<Int>): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        for (c in coins) {
            val r = coin.toMutableList()
            r.add(c)
            res.add(r.sorted())
        }
        return res
    }
}
