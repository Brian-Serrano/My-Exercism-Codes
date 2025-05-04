import kotlin.collections.ArrayDeque

class EmptyBufferException: Exception()

class BufferFullException: Exception()

class CircularBuffer<T>(private val capacity: Int) {

    private val queue = ArrayDeque<T>(capacity)

    fun read() : T {
        if (queue.isEmpty()) {
            throw EmptyBufferException()
        } else {
            return queue.removeFirst()
        }
    }

    fun write(value: T) {
        if (queue.size + 1 > capacity) {
            throw BufferFullException()
        } else {
            queue.add(value)
        }
    }

    fun overwrite(value: T) {
        if (queue.size + 1 > capacity) {
            queue.removeFirst()
        }
        queue.add(value)
    }

    fun clear() {
        queue.clear()
    }
}