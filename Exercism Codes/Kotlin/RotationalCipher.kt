class RotationalCipher(private val key: Int) {
    fun encode(text: String) = text.map {
        if (it.isLetter()) {
            if (it.isLowerCase()) 'a' + (((it - 'a') + key) % 26)
            else 'A' + (((it - 'A') + key) % 26)
        } else it
    }.joinToString("")
}