object Luhn {

    fun isValid(candidate: String): Boolean {
        val cardNum = candidate.replace(" ", "")

        if (!cardNum.all { it.isDigit() } || cardNum.length < 2) {
            return false
        }

        val digits = cardNum.map { it.toString().toInt() }.toMutableList()
        var i = digits.size % 2
        while (i < digits.size) {
            digits[i] *= 2
            if (digits[i] > 9) {
                digits[i] -= 9
            }
            i += 2
        }
        return digits.sum() % 10 == 0
    }
}
