object BeerSong {
    fun verses(startBottles: Int, takeDown: Int): String {
        return (takeDown..startBottles).reversed().joinToString("\n") {
            "${
                if (it == 0) "No more" else it
            } bottle${
                if (it == 1) "" else "s"
            } of beer on the wall, ${
                if (it == 0) "no more" else it
            } bottle${
                if (it == 1) "" else "s"
            } of beer.\n${
                if (it == 0) "Go to the store" else "Take ${
                    if (it == 1) "it" else "one"
                } down"
            } and ${
                if (it == 0) "buy some more" else "pass it around"
            }, ${
                if (it - 1 == 0) "no more" else if (it == 0) 99 else it - 1
            } bottle${
                if (it - 1 == 1) "" else "s"
            } of beer on the wall.\n"
        }
    }
}
