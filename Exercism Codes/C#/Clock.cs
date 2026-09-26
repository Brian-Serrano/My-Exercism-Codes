using System;
public class Clock
{
    private readonly DateTime time;
    public Clock(int hours, int minutes): this(DateTime.Today.Add(new TimeSpan(hours, minutes, 0))) { }
    public Clock(DateTime time) => this.time = time;
    public Clock Add(int minutesToAdd) => new(time.Add(new TimeSpan(0, minutesToAdd, 0)));
    public Clock Subtract(int minutesToSubtract) => new(time.Subtract(new TimeSpan(0, minutesToSubtract, 0)));
    public override string ToString() => $"{time.Hour:00}:{time.Minute:00}";
    public override bool Equals(object obj) => obj is Clock clock && time.Hour == clock.time.Hour && time.Minute == clock.time.Minute;
    public override int GetHashCode() => HashCode.Combine(time.Hour, time.Minute);
}