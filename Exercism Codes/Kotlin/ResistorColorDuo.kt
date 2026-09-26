object ResistorColorDuo {
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
    fun value(vararg colors: Color): Int {
        return "${COLORS.indexOf(colors[0])}${COLORS.indexOf(colors[1])}".toInt()
    }
}