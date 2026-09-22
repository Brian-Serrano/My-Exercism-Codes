object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        val result = mutableListOf<Any>()
        for (element in source) {
            when (element) {
                null -> 0
                is Collection<*> -> result.addAll(flatten(element))
                else -> result.add(element)
            }
        }
        return result
    }
}
