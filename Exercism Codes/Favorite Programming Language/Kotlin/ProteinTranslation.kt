fun translate(rna: String?): List<String> {
    if (rna != null) {
        val proteins = mutableListOf<String>()
        for (codon in rna.chunked(3)) {
            proteins.add(
                when (codon) {
                    "AUG" -> "Methionine"
                    "UUU", "UUC" -> "Phenylalanine"
                    "UUA", "UUG" -> "Leucine"
                    "UCU", "UCC", "UCA", "UCG" -> "Serine"
                    "UAU", "UAC" -> "Tyrosine"
                    "UGU", "UGC" -> "Cysteine"
                    "UGG" -> "Tryptophan"
                    "UAA", "UAG", "UGA" -> return proteins
                    else -> throw IllegalArgumentException("Invalid codon")
                }
            )
        }
        return proteins
    } else return emptyList()
}