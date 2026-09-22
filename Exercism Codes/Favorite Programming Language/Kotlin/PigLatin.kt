object PigLatin {
    fun translate(phrase: String) = phrase.split(" ").joinToString(" ") { str ->
        val newStr = str
            .replaceFirst(Regex("^[^aeiouxyq]+"), "")
            .replaceFirst(Regex("^qu|^q"), "")
            .let { replace(str, it, "y") }
            .let { replace(str, it, "x") }
        newStr + str.substring(0, str.length - newStr.length) + "ay"
    }
    private fun replace(
        orig: String, word: String, char: String
    ) = if (orig[0] == char[0] && Regex("[aeiou]").matches(orig[1].toString())) {
        word.replaceFirst(char, "")
    } else word
}