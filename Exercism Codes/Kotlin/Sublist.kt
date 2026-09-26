enum class Relationship {
    EQUAL, SUBLIST, SUPERLIST, UNEQUAL
}

fun <T> List<T>.relationshipTo(list: List<T>): Relationship {
    if (this == list) {
        return Relationship.EQUAL
    }
    if (this.size > list.size && checkSublist(this, list)) {
        return Relationship.SUPERLIST
    }
    if (this.size < list.size && checkSublist(list, this)) {
        return Relationship.SUBLIST
    }

    return Relationship.UNEQUAL
}

fun <T> checkSublist(listOne: List<T>, listTwo: List<T>): Boolean {
    for (x in 0..(listOne.size - listTwo.size)) {
        if (listOne.subList(x, x + listTwo.size) == listTwo) {
            return true
        }
    }

    return false
}