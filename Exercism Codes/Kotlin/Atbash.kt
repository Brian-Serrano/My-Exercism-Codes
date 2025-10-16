object Atbash {

    fun encode(s: String): String {
        val result = mutableListOf<Char>()
        for (c in s) {
            if (c.isLetterOrDigit()) {
                result.add(transform(c))

                if ((result.size + 1) % 6 == 0) {
                    result.add(' ')
                }
            }
        }

        return result.joinToString("").trim()
    }

    fun decode(s: String): String {
        return s.filter { it.isLetterOrDigit() }.map { transform(it) }.joinToString("")
    }

    fun transform(c: Char): Char {
        return if (c.isLetter()) (97 + (26 - (c.lowercase()[0].code - 97) - 1)).toChar() else c
    }
}
