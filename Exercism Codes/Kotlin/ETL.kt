object ETL {
    fun transform(source: Map<Int, Collection<Char>>): Map<Char, Int> {
        val lst = mutableMapOf<Char, Int>()
        for ((key, value) in source.entries) {
            for (i in value) {
                lst[i.lowercase()[0]] = key
            }
        }
        return lst
    }
}
