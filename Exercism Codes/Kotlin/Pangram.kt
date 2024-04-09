object Pangram {
    fun isPangram(input: String): Boolean {
        return (0..25).all { input.contains((it + 65).toChar(), true) }
    }
}