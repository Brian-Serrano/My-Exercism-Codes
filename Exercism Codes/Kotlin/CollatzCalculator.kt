object CollatzCalculator {
    fun computeStepCount(start: Int): Int {
        require(start > 0)
        var s = start
        var step = 0
        while (s > 1) {
            s = if (s % 2 == 0) s / 2 else (s * 3) + 1
            step++
        }
        return step
    }
}
