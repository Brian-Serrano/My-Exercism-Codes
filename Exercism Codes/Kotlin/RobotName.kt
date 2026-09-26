import kotlin.random.Random
class Robot {
    lateinit var name: String
    init {
        generateName()
    }
    fun reset() {
        names.remove(name)
        generateName()
    }
    private fun generateName() {
        val name = "${genLetter()}${genLetter()}${genNumber()}${genNumber()}${genNumber()}"
        if (!names.add(name)) {
            return generateName()
        }
        this.name = name
    }
    private fun genLetter() = Random.nextInt(65, 91).toChar()
    private fun genNumber() = Random.nextInt(0, 10)
    companion object {
        val names = mutableSetOf<String>()
    }
}