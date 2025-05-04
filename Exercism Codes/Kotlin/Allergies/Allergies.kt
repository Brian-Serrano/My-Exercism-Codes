class Allergies(private val score: Int) {

    fun getList(): List<Allergen> {
        return Allergen.values().filter { it.score == score and it.score }
    }

    fun isAllergicTo(allergen: Allergen): Boolean {
        return getList().contains(allergen)
    }
}
