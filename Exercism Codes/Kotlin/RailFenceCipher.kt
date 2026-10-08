class RailFenceCipher(val rails: Int) {

    fun getEncryptedData(input: String): String {
        return createFence(input.length).joinToString("") { input[it].toString() }
    }

    fun getDecryptedData(input: String): String {
        val fence = createFence(input.length)
        return input.indices.joinToString("") { input[fence.indexOf(it)].toString() }
    }

    private fun createFence(inputLength: Int): List<Int> {
        val cycle = 2 * rails - 2
        return (0..<rails).flatMap {
            x -> (0..<inputLength).filter {
                y -> y % cycle == x || y % cycle == cycle - x
            }
        }
    }
}
