object Isogram {
    fun isIsogram(input: String) = replace(input).length == replace(input).toSet().size
    private fun replace(input: String) = input.lowercase().replace(Regex("[- ]"), "")
}