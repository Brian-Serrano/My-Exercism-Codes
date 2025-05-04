object RomanNumerals {

    private val romanNumbers = listOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")

    fun value(n: Int): String {
        var number = n
        val result = StringBuilder()
        while (number > 0) {
            for (num in romanNumbers) {
                if (number >= num.first) {
                    result.append(num.second)
                    number -= num.first
                    break
                }
            }
        }
        return result.toString()
    }
}
