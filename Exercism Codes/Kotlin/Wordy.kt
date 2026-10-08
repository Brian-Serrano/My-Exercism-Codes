import kotlin.math.pow

object Wordy {

    fun answer(input: String): Int {
        val question = input.substring(0, input.length - 1)
            .split(" ").drop(2)
            .filter { !listOf("by", "to", "the", "power").contains(it) }
            .map { convertPower(it) }
        val isValid = question.any {
            !(it.toIntOrNull() != null || listOf("plus", "minus", "multiplied", "divided", "raised").contains(it))
        }

        if (isValid) {
            throw Exception()
        }
        if (question.size % 2 == 0) {
            throw Exception()
        }
        var count = question[0].toInt()
        for (x in 2..<question.size step 2) {
            if (question[x - 1] == "plus") {
                count += question[x].toInt()
            }
            else if (question[x - 1] == "minus") {
                count -= question[x].toInt()
            }
            else if (question[x - 1] == "multiplied") {
                count *= question[x].toInt()
            }
            else if (question[x - 1] == "divided") {
                count /= question[x].toInt()
            }
            else if (question[x - 1] == "raised") {
                count = count.toFloat().pow(question[x].toInt()).toInt()
            }
            else {
                throw Exception()
            }
        }
        return count
    }

    fun convertPower(input: String): String {
        if (input.length >= 3) {
            val containsTh = listOf("st", "nd", "rd", "th").contains(input.substring(input.length - 2))
            val isNumber = input.substring(0, input.length - 2).toIntOrNull() != null
            if (containsTh && isNumber) {
                return input.substring(0, input.length - 2)
            }
        }
        return input
    }
}
