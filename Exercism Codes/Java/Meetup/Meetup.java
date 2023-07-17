import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.IntStream;

class Meetup {
    private int month;
    private int year;

    public Meetup(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule meetupSchedule) {
        int lastDay = YearMonth.of(year, month).atEndOfMonth().getDayOfMonth();
        return switch(meetupSchedule) {
            case FIRST -> findDay(1, 7,dayOfWeek);
            case SECOND -> findDay(8, 14, dayOfWeek);
            case THIRD -> findDay(15, 21, dayOfWeek);
            case FOURTH -> findDay(22, 28, dayOfWeek);
            case LAST -> findDay(lastDay - 6, lastDay, dayOfWeek);
            case TEENTH -> findDay(13, 19, dayOfWeek);
        };
    }

    public LocalDate findDay(int start, int end, DayOfWeek dayOfWeek) {
        return LocalDate.of(year, month, IntStream.range(start, end + 1)
                .filter(n -> LocalDate.of(year, month, n)
                        .getDayOfWeek() == dayOfWeek)
                .findFirst()
                .orElse(1));
    }
}
