object ResistorColorTrio {
    private val COLORS = listOf(
        Color.BLACK,
        Color.BROWN,
        Color.RED,
        Color.ORANGE,
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.VIOLET,
        Color.GREY,
        Color.WHITE
    )
    fun text(vararg input: Color): String {
        val result = "${COLORS.indexOf(input[0])}${COLORS.indexOf(input[1])}${"0".repeat(COLORS.indexOf(input[2]))}".toInt()
        return when {
            result % 1000000 == 0 -> "${result / 1000000} megaohms"
            result % 1000 == 0 -> "${result / 1000} kiloohms"
            else -> "$result ohms"
        }
    }
}