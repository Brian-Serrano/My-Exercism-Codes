object Acronym {
    fun generate(phrase: String) : String {
        return phrase
            .split(Regex("[ |-]+"))
            .map { it.removeSurrounding("_")[0] }
            .joinToString("")
            .uppercase()
    }
}