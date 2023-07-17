using System;
using System.Linq;

class BirdCount
{
    private int[] birdsPerDay;

    public BirdCount(int[] birdsPerDay)
    {
        this.birdsPerDay = birdsPerDay;
    }

    public static int[] LastWeek()
    {
        return new int[] { 0, 2, 5, 3, 7, 8, 4 };
    }

    public int Today()
    {
        return birdsPerDay[birdsPerDay.Length - 1];
    }

    public void IncrementTodaysCount()
    {
        birdsPerDay[birdsPerDay.Length - 1]++;
    }

    public bool HasDayWithoutBirds()
    {
        return birdsPerDay.Any(b => b <= 0);
    }

    public int CountForFirstDays(int numberOfDays)
    {
        return birdsPerDay.SkipLast(birdsPerDay.Length - numberOfDays).Aggregate((acc, next) => acc + next);
    }

    public int BusyDays()
    {
        return birdsPerDay.Count(b => b >= 5);
    }
}
