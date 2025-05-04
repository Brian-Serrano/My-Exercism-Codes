class KindergartenGarden(private val diagram: String) {

    private val students = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry")

    fun getPlantsOfStudent(student: String): List<String> = diagram.split("\n").flatMap {
        val idx = (students.indexOf(student) + 1) * 2
        listOf(mapPlants(it[idx - 2]), mapPlants(it[idx - 1]))
    }

    private fun mapPlants(char: Char): String = when (char) {
        'G' -> "grass"
        'C' -> "clover"
        'R' -> "radishes"
        'V' -> "violets"
        else -> throw IllegalArgumentException()
    }
}
