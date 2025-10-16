class IsbnVerifier {

    fun isValid(number: String): Boolean {
        val isbn10 = number.replace("-", "")
        if (isbn10.length != 10) return false
        var total = 0
        for (idx in 0 until isbn10.length) {
            val n = isbn10.length - idx
            if (idx == isbn10.length - 1 && isbn10[idx] == 'X') {
                total += 10 * n
                continue
            }
            if (isbn10[idx].isDigit()) {
                total += isbn10[idx].toString().toInt() * n
            }
            else {
                return false
            }
        }

        return total % 11 == 0
    }
}
