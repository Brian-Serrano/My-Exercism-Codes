import kotlin.streams.toList

object AffineCipher {

    fun encode(input: String, a: Int, b: Int): String {
        if (a % 2 == 0 || a % 13 == 0) {
            throw IllegalArgumentException("a and m must be coprime.")
        }
        val enc = input.lowercase().replace(Regex("\\W"), "")
            .chars().map { if (it in 48..57) it - 'a'.code else ((a * (it - 'a'.code)) + b) % 26 }
            .toList().joinToString("") { (it + 'a'.code).toChar().toString() }
        return Regex(".{1,5}").findAll(enc).joinToString(" ") { it.value }
    }

    fun decode(input: String, a: Int, b: Int): String {
        if (a % 2 == 0 || a % 13 == 0) {
            throw IllegalArgumentException("a and m must be coprime.")
        }
        return input.lowercase().replace(Regex("\\W"), "")
            .chars().map { if (it in 48..57) it else (mmi(a) * ((it - 'a'.code) - b)) % 26 }
            .toList().joinToString("") { (if (it in 48..57) it else ((it + 26) % 26) + 'a'.code).toChar().toString() }
    }

    fun mmi(a: Int): Int {
        for (i in 1..25) {
            if (a * i % 26 == 1) {
                return i
            }
        }
        return -1
    }
}
