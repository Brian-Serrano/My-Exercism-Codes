class PhoneNumber(phoneNumber: String) {

    private val pattern = "(?:\\+1 *|)(?:([2-9][0-9]{2})|\\(([2-9][0-9]{2})\\))[- .]+([2-9][0-9]{2})[- .]+([0-9]{4})|1([2-9][0-9]{2}[2-9][0-9]{6})"

    init {
        require(Regex(pattern).matches(phoneNumber.trim()))
    }

    val number: String = Regex(pattern)
        .find(phoneNumber.trim())!!
        .groupValues
        .drop(1)
        .joinToString("")
}
