class Reactor<T> {
    interface Subscription {
        fun cancel()
    }

    class CellSubscription(private val removeObserver: () -> Unit) : Subscription {
        override fun cancel() {
            removeObserver()
        }
    }

    abstract inner class Cell<T> {
        lateinit var inputs: List<Cell<T>>
        lateinit var observers: MutableList<Observer<T>>

        abstract val value: T
    }

    inner class InputCell<T>(initialValue: T) : Cell<T>() {

        init {
            observers = mutableListOf()
        }

        override var value: T = initialValue
            set(newValue) {
                val values = mutableListOf<T>()

                for (observer in observers) {
                    values.add(observer.cell.value)
                }

                field = newValue

                for (i in observers.indices) {
                    val newValue = observers[i].cell.value

                    if (newValue != values[i]) {
                        observers[i].callback(newValue)
                    }
                }
            }
    }

    inner class Observer<T>(val cell: ComputeCell<T>, val callback: (T) -> Unit)

    inner class ComputeCell<T>(vararg inputs: Cell<T>, func: (List<T>) -> T) : Cell<T>() {
        val function: (List<T>) -> T = func

        init {
            this.inputs = inputs.toList()
        }

        override val value: T
            get() = function(inputs.map { it.value })

        fun addCallback(callback: (T) -> Unit): Subscription {
            add(callback, inputs, mutableSetOf())

            return CellSubscription {
                for (input in inputs) {
                    val callbacks = input.observers.map { it.callback }
                    if (callbacks.contains(callback)) {
                        input.observers.removeAt(callbacks.indexOf(callback))
                    }
                }
            };
        }

        fun add(callback: (T) -> Unit, inputs: List<Cell<T>>, visited: MutableSet<Cell<T>>) {
            for (input in inputs) {
                if (!visited.add(input)) {
                    continue
                }

                if (input::class == InputCell::class) {
                    input.observers.add(Observer(this, callback))
                }
                else {
                    add(callback, input.inputs, visited)
                }
            }
        }
    }
}
