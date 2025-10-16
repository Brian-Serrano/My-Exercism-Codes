class CustomSet() {

    private var elements: MutableSet<Int> = mutableSetOf()

    constructor(vararg num: Int) : this() {
        for (x in num) {
            elements.add(x)
        }
    }

    constructor(elements: MutableSet<Int>): this() {
        this.elements = elements
    }

    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    fun isSubset(other: CustomSet): Boolean {
        return other.elements.containsAll(elements)
    }

    fun isDisjoint(other: CustomSet): Boolean {
        return elements.intersect(other.elements).isEmpty()
    }

    fun contains(other: Int): Boolean {
        return elements.contains(other)
    }

    fun intersection(other: CustomSet): CustomSet {
        return CustomSet(elements.intersect(other.elements).toMutableSet())
    }

    fun add(other: Int) {
        elements.add(other)
    }

    override fun equals(other: Any?): Boolean {
        return elements == (other as CustomSet).elements
    }

    operator fun plus(other: CustomSet): CustomSet {
        return CustomSet(elements.plus(other.elements).toMutableSet())
    }

    operator fun minus(other: CustomSet): CustomSet {
        return CustomSet(elements.minus(other.elements).toMutableSet())
    }
}