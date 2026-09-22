import kotlin.math.pow

object Prime {

    fun nth(n: Int): Int {
        if (n <= 0) {
            throw IllegalArgumentException("There is no zeroth prime.")
        }
        if (n == 1) {
            return 2
        }
        var i = 1
        var count = 1

        while (count != n) {
            i += 2
            if (isPrime(i)) {
                count++
            }
        }
        return i
    }

    fun isPrime(num: Int): Boolean {
        return (3..(num.toDouble().pow(0.5).toInt() + 1) step 2).none { num % it == 0 }
    }
}
