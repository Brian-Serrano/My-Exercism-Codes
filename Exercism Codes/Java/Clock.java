import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

class Clock {

    LocalDateTime time;

    Clock(int hours, int minutes) {
        time = LocalDate.now().atStartOfDay().plusHours(hours).plusMinutes(minutes);
    }

    void add(int minutes) {
        time = time.plusMinutes(minutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clock clock = (Clock) o;
        return time.getHour() == clock.time.getHour() && time.getMinute() == clock.time.getMinute();
    }

    @Override
    public int hashCode() {
        return Objects.hash(time.getHour(), time.getMinute());
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", time.getHour(), time.getMinute());
    }
}