class Deque<T> {
    private val list = mutableListOf<T>()
    fun push(value: T) = list.add(value)
    fun pop() = list.removeLast()
    fun unshift(value: T) = list.add(0, value)
    fun shift() = list.removeFirst()
}