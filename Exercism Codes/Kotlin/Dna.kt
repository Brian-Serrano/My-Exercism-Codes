class Dna(private val dna: String) {

    init {
        require("[ACGT]*".toRegex().matches(dna))
    }

    val nucleotideCounts: Map<Char, Int>
        get() = dna.fold(mapOf('A' to 0, 'C' to 0, 'G' to 0, 'T' to 0)) { a, c ->
            a.mapValues { if (it.key == c) it.value + 1 else it.value }
        }
}
