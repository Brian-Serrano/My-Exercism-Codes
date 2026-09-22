import kotlin.math.min

class DiamondPrinter {

    fun printToList(letter: Char): List<String> {
        val bufferLength = ((letter - 'A') * 2) + 1
        return List(bufferLength) { idx ->
            val buffer = MutableList(bufferLength) { ' ' }
            val currIdx = min(idx, (bufferLength - 1) - idx)
            buffer[(bufferLength / 2) - currIdx] = 'A' + currIdx
            buffer[(bufferLength / 2) + currIdx] = 'A' + currIdx
            buffer.joinToString("")
        }
    }
}
