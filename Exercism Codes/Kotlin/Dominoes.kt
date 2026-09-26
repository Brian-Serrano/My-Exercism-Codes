class ChainNotFoundException(msg: String) : RuntimeException(msg)

data class Domino(val left: Int, val right: Int)

object Dominoes {

    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        return form(inputDominoes)
    }

    fun formChain(vararg inputDominoes: Domino): List<Domino> {
        return form(inputDominoes.toList())
    }

    fun form(inputDominoes: List<Domino>): List<Domino> {
        val input = inputDominoes.toMutableList()
        val output = mutableListOf<Domino>()

        var x = 0
        var y = 0

        if (input.isNotEmpty()) {
            output.add(input.removeFirst())
        }

        while (true) {
            if (input.isEmpty()) {
                if (output.isEmpty()) {
                    break
                }
                if (output[0].left != output[output.size - 1].right) {
                    throw ChainNotFoundException("Dominoes can not be empty")
                }
                break
            }
            if (output[0].left == input[0].left) {
                output.add(0, Domino(input[0].right, input[0].left))
                input.removeFirst()
                x = 0
                y = 0
                continue
            }
            if (output[0].left == input[0].right) {
                output.addFirst(input[0])
                input.removeFirst()
                x = 0
                y = 0
                continue
            }
            if (output[output.size - 1].right == input[0].left) {
                output.addLast(input[0])
                input.removeFirst()
                x = 0
                y = 0
                continue
            }
            if (output[output.size - 1].right == input[0].right) {
                output.addLast(Domino(input[0].right, input[0].left))
                input.removeFirst()
                x = 0
                y = 0
                continue
            }
            input.addLast(input.removeFirst())
            if (++x >= input.size) {
                if (output[0].left != output[output.size - 1].right) {
                    throw ChainNotFoundException("Dominoes can not be empty")
                }
                else {
                    output.addLast(output.removeFirst())
                    x = 0
                    if (++y >= output.size) {
                        throw ChainNotFoundException("Dominoes can not be empty")
                    }
                }
            }
        }

        return output
    }
}
