import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.sqrt
object CryptoSquare {
    fun ciphertext(plaintext: String): String {
        val normalized = plaintext.filter { it.isLetterOrDigit() }.lowercase()
        val sqrt = sqrt(normalized.length.toDouble())
        val columnSize = ceil(sqrt).toInt()
        val paddedNormalize = normalized + " ".repeat((columnSize * round(sqrt).toInt()) - normalized.length)
        val squared = Regex(".{$columnSize}").findAll(paddedNormalize)
        return List(columnSize) { idx -> squared.map { it.value[idx] }.joinToString("") }.joinToString(" ")
    }
}