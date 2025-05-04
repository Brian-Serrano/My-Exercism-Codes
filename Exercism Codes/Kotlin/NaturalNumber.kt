
enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {
    if (naturalNumber > 0) {
        val sumOfFactors = (1 until naturalNumber).filter { naturalNumber % it == 0 }.sum()
        return if (sumOfFactors == naturalNumber) {
            Classification.PERFECT
        } else if (sumOfFactors > naturalNumber) {
            Classification.ABUNDANT
        } else Classification.DEFICIENT
    } else throw RuntimeException()
}
