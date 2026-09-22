import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Gigasecond {

    static LocalDateTime add(LocalDate moment) {
        moment.atStartOfDay().plus(1000000000, ChronoUnit.SECONDS)
    }

    static LocalDateTime add(LocalDateTime moment) {
        moment.plus(1000000000, ChronoUnit.SECONDS)
    }
}
