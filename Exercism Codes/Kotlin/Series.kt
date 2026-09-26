object Series {

    fun slices(n: Int, s: String): List<List<Int>> {
        require(n <= s.length && n > 0 && s.isNotEmpty())
        return s.windowed(n).map { char -> char.map { it.digitToInt() } }
    }
}
