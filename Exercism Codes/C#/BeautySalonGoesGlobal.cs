using System;
using System.Globalization;

public enum Location
{
    NewYork,
    London,
    Paris
}

public enum AlertLevel
{
    Early,
    Standard,
    Late
}

public static class Appointment
{
    public static DateTime ShowLocalTime(DateTime dtUtc)
    {
        return dtUtc.ToLocalTime();
    }

    public static DateTime Schedule(string appointmentDateDescription, Location location)
    {
        return TimeZoneInfo.ConvertTimeToUtc(DateTime.Parse(appointmentDateDescription), TimeZoneInfo.FindSystemTimeZoneById(GetTimeZoneID(location)));
    }

    public static DateTime GetAlertTime(DateTime appointment, AlertLevel alertLevel)
    {
        return appointment.AddMinutes(-(alertLevel switch
        {
            AlertLevel.Early => 24 * 60,
            AlertLevel.Standard => 105,
            AlertLevel.Late => 30,
            _ => throw new NotImplementedException()
        }));
    }

    public static bool HasDaylightSavingChanged(DateTime dt, Location location)
    {
        TimeZoneInfo tzi = TimeZoneInfo.FindSystemTimeZoneById(GetTimeZoneID(location));
        return tzi.IsDaylightSavingTime(dt) != tzi.IsDaylightSavingTime(dt.AddDays(-7));
    }

    public static DateTime NormalizeDateTime(string dtStr, Location location)
    {
        return DateTime.TryParse(dtStr, CultureInfo.GetCultureInfo(location switch
        {
            Location.NewYork => "en-US",
            Location.London => "en-GB",
            Location.Paris => "fr-FR",
            _ => throw new NotImplementedException(),
        }), DateTimeStyles.None, out DateTime dateTime) ? dateTime : new(1, 1, 1);
    }

    private static string GetTimeZoneID(Location location)
    {
        return location switch
        {
            Location.NewYork => OperatingSystem.IsWindows() ? "Eastern Standard Time" : "America/New_York",
            Location.London => OperatingSystem.IsWindows() ? "GMT Standard Time" : "Europe/London",
            Location.Paris => OperatingSystem.IsWindows() ? "W. Europe Standard Time" : "Europe/Paris",
            _ => throw new NotImplementedException()
        };
    }
}
