class Squares(private val num: Int) {
    fun sumOfSquares(): Int {
        return (1..num).sumOf { it * it }
    }
    fun squareOfSum(): Int {
        return (1..num).sum() * (1..num).sum()
    }
    fun difference(): Int {
        return squareOfSum() - sumOfSquares()
    }
}