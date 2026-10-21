object BottleSong {
    private val numbers = arrayOf(
        arrayOf("One", "no"),
        arrayOf("Two", "one"),
        arrayOf("Three", "two"),
        arrayOf("Four", "three"),
        arrayOf("Five", "four"),
        arrayOf("Six", "five"),
        arrayOf("Seven", "six"),
        arrayOf("Eight", "seven"),
        arrayOf("Nine", "eight"),
        arrayOf("Ten", "nine")
    )

    fun recite(startBottles : Int, takeDown : Int) : String {
        val result = StringBuilder()

        for (i in startBottles downTo startBottles - takeDown + 1) {
            val n = numbers[i - 1][0]
            val bottle1 = if (n == "One") "bottle" else "bottles"
            result.append("$n green $bottle1 hanging on the wall,\n")
            result.append("$n green $bottle1 hanging on the wall,\n")

            result.append("And if one green bottle should accidentally fall,\n")

            val s = numbers[i - 1][1]
            val bottle2 = if (s == "one") "bottle" else "bottles"
            result.append("There'll be $s green $bottle2 hanging on the wall.")

            if (i != startBottles - takeDown + 1)
                result.append("\n\n")
        }

        return result.toString()
    }
}
