import java.time.LocalDate

class Clock {

    private var time = LocalDate.now().atStartOfDay()

    constructor(hours: Int, minutes: Int) {
        time = time.plusHours(hours.toLong()).plusMinutes(minutes.toLong())
    }

    fun subtract(minutes: Int) {
        time = time.minusMinutes(minutes.toLong())
    }

    fun add(minutes: Int) {
        time = time.plusMinutes(minutes.toLong())
    }

    override fun equals(other: Any?): Boolean {
        return other is Clock && time.hour == other.time.hour && time.minute == other.time.minute
    }

    override fun hashCode(): Int {
        return time.hour.hashCode() + time.minute.hashCode()
    }

    override fun toString(): String {
        return String.format("%02d:%02d", time.hour, time.minute)
    }
}
