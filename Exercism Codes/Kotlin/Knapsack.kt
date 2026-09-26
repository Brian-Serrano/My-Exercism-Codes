data class Item(val weight: Int, val value: Int)

fun knapsack(maximumWeight: Int, items: List<Item>): Int {
    val dp = List(items.size + 1) { MutableList(maximumWeight + 1) { 0 } }

    for (i in 1..items.size) {
        val item = items[i - 1]
        for (j in 1..maximumWeight) {
            if (item.weight <= j) {
                dp[i][j] = dp[i - 1][j].coerceAtLeast(dp[i - 1][j - item.weight] + item.value)
            }
            else {
                dp[i][j] = dp[i - 1][j]
            }
        }
    }

    return dp[items.size][maximumWeight]
}
