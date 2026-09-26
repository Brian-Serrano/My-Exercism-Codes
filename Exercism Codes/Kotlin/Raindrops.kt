object Raindrops {
    fun convert(n: Int): String {
        var result: String = ""
        if (n % 3 == 0) {
            result += "Pling"
        }
        if (n % 5 == 0) {
            result += "Plang"
        }
        if (n % 7 == 0) {
            result += "Plong"
        }
        return if (result.isEmpty()) n.toString() else result
    }
}