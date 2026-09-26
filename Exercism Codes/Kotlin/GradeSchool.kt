class School {

    val students = mutableMapOf<Int, MutableList<String>>()

    fun add(student: String, grade: Int) {
        if (!roster().contains(student)) {
            students.computeIfAbsent(grade) { mutableListOf() }.add(student)
        }
    }

    fun grade(grade: Int): List<String> {
        return (students[grade] ?: emptyList()).sorted()
    }

    fun roster(): List<String> {
        return students.toSortedMap().values.flatMap { it.sorted() }
    }
}
