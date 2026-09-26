import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.Temporal
import java.util.*

class Gigasecond {

    constructor(date : LocalDate) {
        this.date = date.atStartOfDay().plusSeconds(1000000000)
    }

    constructor(date : LocalDateTime) {
        this.date = date.plusSeconds(1000000000)
    }

    var date: LocalDateTime? = null
}
