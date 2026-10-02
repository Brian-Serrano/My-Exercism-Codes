object SpiralMatrix {

    fun ofSize(size: Int): Array<IntArray> {
        val matrix = Array(size) { IntArray(size) }
        var verIdx = 0
        var horIdx = 0
        var direction = 0
        if (size * size > 0) {
            matrix[0][0] = 1
        }
        for (i in 1..<(size * size)) {
            when (direction) {
                0 -> {
                    if (matrix[0].size > horIdx + 1 && matrix[verIdx][horIdx + 1] == 0) {
                        horIdx++
                    }
                    else {
                        verIdx++
                        direction = 1
                    }
                }
                1 -> {
                    if (matrix.size > verIdx + 1 && matrix[verIdx + 1][horIdx] == 0) {
                        verIdx++
                    }
                    else {
                        horIdx--
                        direction = 2
                    }
                }
                2 -> {
                    if (horIdx - 1 >= 0 && matrix[verIdx][horIdx - 1] == 0) {
                        horIdx--
                    }
                    else {
                        verIdx--
                        direction = 3
                    }
                }
                3 -> {
                    if (verIdx - 1 >= 0 && matrix[verIdx - 1][horIdx] == 0) {
                        verIdx--
                    }
                    else {
                        horIdx++
                        direction = 0
                    }
                }
            }
            matrix[verIdx][horIdx] = i + 1
        }
        return matrix
    }
}
