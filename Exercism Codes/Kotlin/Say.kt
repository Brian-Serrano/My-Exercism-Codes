class NumberSpeller {

    private val ones = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    private val tens = arrayOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    private val teens = arrayOf("", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")

    fun say(input: Long): String {
        if (input == 0L) {
            return "zero"
        }
        if (input < 0 || input > 999999999999) {
            throw IllegalArgumentException()
        }

        val stringed = input.toString()
        val result = mutableListOf<String>()
        for (x in stringed.length - 1 downTo 0) {
            val idx = stringed.length - x - 1
            if (idx / 3.0 == 1.0) {
                result.add(0, "thousand")
            }
            if (idx / 3.0 == 2.0) {
                if (stringed.substring(x + 1, x + 4).toInt() == 0) {
                    result.removeAt(0)
                }
                result.add(0, "million")
            }
            if (idx / 3.0 == 3.0) {
                if (stringed.substring(x + 1, x + 4).toInt() == 0) {
                    result.removeAt(0)
                }
                result.add(0, "billion")
            }

            val num = stringed[x].toString().toInt()
            if (num != 0) {
                if (idx % 3 == 0) {
                    result.add(0, ones[num])
                }
                if (idx % 3 == 1) {
                    val nextNum = stringed[x + 1].toString().toInt()
                    if (num == 1 && nextNum != 0) {
                        result.removeAt(0)
                        result.add(0, teens[nextNum])
                    }
                    else {
                        result.add(0, tens[num])
                    }
                }
                if (idx % 3 == 2) {
                    result.add(0, "hundred")
                    result.add(0, ones[num])
                }
            }
        }

        return join(result)
    }

    private fun join(lst: List<String>): String {
        var result = lst[0]

        for (x in 1 until lst.size) {
            if (tens.contains(lst[x - 1]) && ones.contains(lst[x])) {
                result += "-" + lst[x]
            }
            else {
                result += " " + lst[x]
            }
        }

        return result
    }
}