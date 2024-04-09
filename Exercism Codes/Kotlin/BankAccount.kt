class BankAccount {
    var balance = 0L
        get() = if (isClosed) throw IllegalStateException() else field
    private var isClosed = false
    @Synchronized fun adjustBalance(amount: Long) {
        if (isClosed) throw IllegalStateException() else balance += amount
    }
    fun close() {
        if (isClosed) throw IllegalStateException() else isClosed = true
    }
}