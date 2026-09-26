import kotlin.math.pow

class BaseConverter(private val base: Int, private val numbers: IntArray) {

    init {
        require(base >= 2) { "Bases must be at least 2." }
        require(numbers.isNotEmpty()) { "You must supply at least one digit." }
        require(!(numbers.size > 1 && numbers[0] == 0)) { "Digits may not contain leading zeros." }
        require(numbers.all { it >= 0 }) { "Digits may not be negative." }
        require(numbers.all { it < base }) { "All digits must be strictly less than the base." }
    }

    fun convertToBase(newBase: Int): IntArray {
        require(newBase >= 2) { "Bases must be at least 2." }
        var base10 = numbers
            .mapIndexed { idx, num -> num * base.toDouble().pow(numbers.size - 1.0 - idx).toInt() }
            .sum()
        val result = mutableListOf<Int>()
        do {
            result.add(0, base10 % newBase)
            base10 /= newBase
        } while (base10 > 0)
        return result.toIntArray()
    }
}
