object Bob {
    fun hey(input: String) = when {
        questionMark(input) && isUppercase(input) -> "Calm down, I know what I'm doing!"
        questionMark(input) -> "Sure."
        isUppercase(input) -> "Whoa, chill out!"
        input.trim().isEmpty() -> "Fine. Be that way!"
        else -> "Whatever."
    }
    private fun questionMark(input: String) = input.trim().endsWith("?")
    private fun isUppercase(input: String) = input.uppercase() == input && "[A-Za-z]".toRegex().containsMatchIn(input)
}