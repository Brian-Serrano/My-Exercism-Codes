object PascalsTriangle {

    fun computeTriangle(rows: Int): List<List<Int>> {
        var prevArr = mutableListOf(1)
        val result = mutableListOf<MutableList<Int>>()

        for (i in 0..<rows) {
            val arr = mutableListOf<Int>()
            for (j in 0..<(i + 1)) {
                arr.add(checkBounds(prevArr, j) + checkBounds(prevArr, j - 1))
            }
            result.add(arr)
            prevArr = arr
        }
        return result
    }

    fun checkBounds(arr: List<Int>, idx: Int): Int {
        return if (idx < arr.size && idx >= 0) arr[idx] else 0
    }
}
