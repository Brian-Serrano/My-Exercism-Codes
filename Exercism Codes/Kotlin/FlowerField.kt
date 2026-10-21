data class FlowerFieldBoard(val board: List<String>) {

    fun withNumbers(): List<String> {
        val matrix = board.map { x -> x.map { y -> y.toString() }.toMutableList() }

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] != "*") {
                    var k = 0
                    if (i + 1 < matrix.size && matrix[i + 1][j] == "*") k++
                    if (i - 1 >= 0 && matrix[i - 1][j] == "*") k++
                    if (j + 1 < matrix[i].size && matrix[i][j + 1] == "*") k++
                    if (j - 1 >= 0 && matrix[i][j - 1] == "*") k++
                    if (i + 1 < matrix.size && j + 1 < matrix[i].size && matrix[i + 1][j + 1] == "*") k++
                    if (i + 1 < matrix.size && j - 1 >= 0 && matrix[i + 1][j - 1] == "*") k++
                    if (i - 1 >= 0 && j + 1 < matrix[i].size && matrix[i - 1][j + 1] == "*") k++
                    if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] == "*") k++

                    if (k > 0)
                        matrix[i][j] = k.toString()
                }
            }
        }

        return matrix.map { it.joinToString("") }
    }
}
