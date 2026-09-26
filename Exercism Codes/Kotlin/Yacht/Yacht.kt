object Yacht {

    fun solve(category: YachtCategory, vararg dices: Int): Int {
        val categoriesFunction = listOf(
            { x: List<Int> -> if (x.distinct().size == 1) 50 else 0 },
            { x: List<Int> -> x.sumOf { d -> if (d == 1) d else 0 } },
            { x: List<Int> -> x.sumOf { d -> if (d == 2) d else 0 } },
            { x: List<Int> -> x.sumOf { d -> if (d == 3) d else 0 } },
            { x: List<Int> -> x.sumOf { d -> if (d == 4) d else 0 } },
            { x: List<Int> -> x.sumOf { d -> if (d == 5) d else 0 } },
            { x: List<Int> -> x.sumOf { d -> if (d == 6) d else 0 } },
            { x: List<Int> -> if (setOf(2, 3) == x.distinct().map { d -> x.count { it == d } }.toSet()) x.sum() else 0 },
            { x: List<Int> -> x.distinct().filter { d -> x.count { it == d } >= 4 }.sum() * 4 },
            { x: List<Int> -> if (setOf(1, 2, 3, 4, 5) == x.toSet()) 30 else 0 },
            { x: List<Int> -> if (setOf(2, 3, 4, 5, 6) == x.toSet()) 30 else 0 },
            { x: List<Int> -> x.sum() }
        )

        return categoriesFunction[category.ordinal](dices.toList())
    }
}
