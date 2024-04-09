class Triangle<out T : Number>(a: T, b: T, c: T) {
    private val da = a.toDouble()
    private val db = b.toDouble()
    private val dc = c.toDouble()
    init {
        if (
            !(da + db >= dc && db + dc >= da && da + dc >= db) ||
            (da == 0.0 || db == 0.0 || dc == 0.0)
            ) {
            throw IllegalArgumentException()
        }
    }
    val isEquilateral: Boolean = da == db && db == dc
    val isIsosceles: Boolean = da == db || da == dc || db == dc
    val isScalene: Boolean = da != db && db != dc && da != dc
}