import kotlin.random.Random

data class Cipher(var key: String = "*") {

    init {
        if (key.any { it.isUpperCase() || it.isDigit() } || key.isEmpty()) {
            throw IllegalArgumentException()
        }
        if (key == "*") {
            key = (0..99).map { Random.nextInt(97, 123).toChar() }.joinToString("")
        }
    }

    fun encode(s: String): String {
        return s.mapIndexed { idx, x -> (97 + (((x.code - 97) + (key[idx % key.length].code - 97)) % 26)).toChar() }.joinToString("")
    }

    fun decode(s: String): String {
        return s.mapIndexed { idx, x ->
            val mapped = ((x.code - 97) - (key[idx % key.length].code - 97)) % 26
            (97 + (mapped + (if (mapped < 0) 26 else 0))).toChar().toString()
        }.joinToString("")
    }
}