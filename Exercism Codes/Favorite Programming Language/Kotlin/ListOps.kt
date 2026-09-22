fun <T> List<T>.customAppend(list: List<T>): List<T> {
    return this.plus(list)
}

fun List<Any>.customConcat(): List<Any> {
    val result = mutableListOf<Any>()
    for (x in this) {
        result.addAll(x as Collection<Any>)
    }
    for (x in result) {
        if (x is List<*>) {
            return result.customConcat()
        }
    }
    return result
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    return this.filter(predicate)
}

val List<Any>.customSize: Int get() = this.size

fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    return this.map(transform)
}

fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    return this.fold(initial, f)
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    return this.foldRight(initial, f)
}

fun <T> List<T>.customReverse(): List<T> {
    return this.reversed()
}
