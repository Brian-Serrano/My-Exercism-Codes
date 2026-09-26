object WordCount {
    fun phrase(phrase: String) = Regex("\\b[A-Za-z0-9']+\\b")
        .findAll(phrase.lowercase())
        .map(MatchResult::value)
        .groupBy { it }
        .mapValues { it.value.size }
}