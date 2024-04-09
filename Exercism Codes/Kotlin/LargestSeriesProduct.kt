class Series(input: String) {
    private val input: String
    init {
        if ("\\d+".toRegex().matches(input)) {
            this.input = input
        } else throw IllegalArgumentException()
    }
    fun getLargestProduct(span: Int): Long {
        return try {
            (0..(input.length - span)).maxOf { idx ->
                input.substring(idx until (idx + span))
                    .map { it.digitToInt().toLong() }
                    .reduce { a, c -> a * c }
            }
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }
}