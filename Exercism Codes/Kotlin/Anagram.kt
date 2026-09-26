class Anagram(private val source: String) {
    fun match(anagrams: Collection<String>): Set<String> {
        return anagrams.filter {
            source.lowercase().toList().sorted() == it.lowercase().toList().sorted() &&
            source.lowercase() != it.lowercase()
        }.toSet()
    }
}