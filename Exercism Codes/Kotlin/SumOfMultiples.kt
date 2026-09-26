object SumOfMultiples {

    fun sum(factors: Set<Int>, limit: Int): Int = factors.filter { it > 0 }
        .flatMap { fac -> (1 until limit).filter { it % fac == 0 } }
        .toSet().sum()
}
