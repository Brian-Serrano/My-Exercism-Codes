data class MatrixCoordinate(val row: Int, val col: Int)
class Matrix(matrix: List<List<Int>>) {
    val saddlePoints = matrix.asSequence()
        .mapIndexed { rowIdx, row -> row.mapIndexed { colIdx, item -> Pair(item, MatrixCoordinate(rowIdx + 1, colIdx + 1)) } }
        .map { row -> row.filterIndexed { idx, item -> row.maxOf { it.first } == item.first && matrix.minOf { it[idx] } == item.first } }
        .flatten().map { it.second }.toSet()
}