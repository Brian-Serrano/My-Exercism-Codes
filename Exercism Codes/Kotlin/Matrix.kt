class Matrix(matrixAsString: String) {
    private var matrix: List<List<Int>> = matrixAsString.split("\n")
        .map { row -> row.split(" ").map { it.toInt() } }
    fun column(colNr: Int): List<Int> {
        return matrix.map { it[colNr - 1] }
    }
    fun row(rowNr: Int): List<Int> {
        return matrix[rowNr - 1]
    }
}