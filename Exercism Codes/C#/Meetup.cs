public enum Schedule
{
    Teenth,
    First,
    Second,
    Third,
    Fourth,
    Last
}

public class Meetup
{
    public int Month { get; }
    public int Year { get; }

    public Meetup(int month, int year)
    {
        Month = month;
        Year = year;
    }

    public DateTime Day(DayOfWeek dayOfWeek, Schedule schedule)
    {
        int lastDay = DateTime.DaysInMonth(Year, Month);
        return schedule switch
        {
            Schedule.Teenth => FindDay(13, 19, dayOfWeek),
            Schedule.First => FindDay(1, 7, dayOfWeek),
            Schedule.Second => FindDay(8, 14, dayOfWeek),
            Schedule.Third => FindDay(15, 21, dayOfWeek),
            Schedule.Fourth => FindDay(22, 28, dayOfWeek),
            Schedule.Last => FindDay(lastDay - 6, lastDay, dayOfWeek),
            _ => throw new ArgumentOutOfRangeException()
        };
    }

    private DateTime FindDay(int start, int end, DayOfWeek dayOfWeek)
    {
        for (int day = start; day <= end; day++)
        {
            DateTime date = new DateTime(Year, Month, day);
            if (date.DayOfWeek == dayOfWeek)
            {
                return date;
            }
        }
        throw new ArgumentException("No matching day found.");
    }
}