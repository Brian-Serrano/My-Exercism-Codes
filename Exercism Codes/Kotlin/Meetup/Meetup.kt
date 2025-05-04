import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class Meetup(private val month: Int, private val year: Int) {

    fun day(dayOfWeek: DayOfWeek, schedule: MeetupSchedule): LocalDate {
        return when (schedule) {
            MeetupSchedule.FIRST -> getDay(1, dayOfWeek)
            MeetupSchedule.SECOND -> getDay(2, dayOfWeek)
            MeetupSchedule.THIRD -> getDay(3, dayOfWeek)
            MeetupSchedule.FOURTH -> getDay(4, dayOfWeek)
            MeetupSchedule.LAST -> getDay(-1, dayOfWeek)
            MeetupSchedule.TEENTH -> {
                LocalDate.of(year, month, (13..19).first { LocalDate.of(year, month, it).dayOfWeek == dayOfWeek })
            }
        }
    }

    private fun getDay(ordinal: Int, dayOfWeek: DayOfWeek): LocalDate {
        return LocalDate.of(year, month, 1)
            .with(TemporalAdjusters.dayOfWeekInMonth(ordinal, dayOfWeek))
    }
}
