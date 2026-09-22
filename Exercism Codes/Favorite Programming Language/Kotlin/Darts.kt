import kotlin.math.sqrt

object Darts {

    fun <T: Number> score(x: T, y: T): Int {
        val d = sqrt(x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble())
        return when {
            d > 10 -> 0
            d > 5 -> 1
            d > 1 -> 5
            else -> 10
        }
    }
}
