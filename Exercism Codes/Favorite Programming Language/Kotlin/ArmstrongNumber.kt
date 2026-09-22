import kotlin.math.pow

object ArmstrongNumber {

    fun check(input: Int) = input.toString()
        .let { inp -> inp.sumOf { it.digitToInt().toDouble().pow(inp.length).toInt() } == input }

}
