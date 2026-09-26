class Scale(tonic: String) {

    private val tonic: String = when (tonic.capitalize()) {
        "A#" -> "Bb"
        "C#" -> "Db"
        "D#" -> "Eb"
        "F#" -> "Gb"
        "G#" -> "Ab"
        else -> tonic.capitalize()
    }

    private val notes = listOf("A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab")
    private val interval = "mMA"

    fun chromatic(): List<String> {
        return notes.subList(
            notes.indexOf(tonic), notes.size
        ) + notes.subList(
            0, notes.indexOf(tonic)
        )
    }

    fun interval(intervals: String): List<String> {
        var currentIdx = notes.indexOf(tonic)
        return intervals.map {
            val temp = currentIdx
            currentIdx = (currentIdx + (interval.indexOf(it) + 1)) % 12
            notes[temp]
        }
    }
}
