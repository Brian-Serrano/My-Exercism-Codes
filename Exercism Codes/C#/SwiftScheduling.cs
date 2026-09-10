public static class SwiftScheduling
{
    public static DateTime DeliveryDate(DateTime meetingStart, string description)
    {
        if (description == "NOW")
        {
            return meetingStart.AddHours(2);
        }
        if (description == "ASAP")
        {
            if (meetingStart.Hour < 13)
            {
                return new DateTime(meetingStart.Year, meetingStart.Month, meetingStart.Day, 17, 0, 0);
            }
            else
            {
                return new DateTime(meetingStart.Year, meetingStart.Month, meetingStart.Day + 1, 13, 0, 0);
            }
        }
        if (description == "EOW")
        {
            int dayOfWeek = (int)meetingStart.DayOfWeek;
            if (dayOfWeek >= 1 && dayOfWeek <= 3)
            {
                return new DateTime(meetingStart.Year, meetingStart.Month, meetingStart.Day, 17, 0, 0).AddDays(5 - dayOfWeek);
            }
            else
            {
                return new DateTime(meetingStart.Year, meetingStart.Month, meetingStart.Day, 20, 0, 0).AddDays(7 - dayOfWeek);
            }
        }
        if (description[^1] == 'M')
        {
            int n = int.Parse(description[..^1]);
            DateTime newMonth = new DateTime(meetingStart.Year + (meetingStart.Month < n ? 0 : 1), n, 1);
            return FindWorkday(newMonth, false);
        }
        if (description[0] == 'Q')
        {
            int n = int.Parse(description[1..]);
            DateTime newMonth = new DateTime(meetingStart.Year + (meetingStart.Month < (n * 3) + 1 ? 0 : 1), n * 3, 1);
            return FindWorkday(new DateTime(newMonth.Year, newMonth.Month, DateTime.DaysInMonth(newMonth.Year, newMonth.Month)), true);
        }

        return DateTime.Now;
    }

    private static DateTime FindWorkday(DateTime date, bool reverse)
    {
        while (date.DayOfWeek == DayOfWeek.Saturday || date.DayOfWeek == DayOfWeek.Sunday)
        {
            date = date.AddDays(reverse ? -1 : 1);
        }
        
        return new DateTime(date.Year, date.Month, date.Day, 8, 0, 0);
    }
}
