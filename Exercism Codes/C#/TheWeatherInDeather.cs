using System;
using System.Collections.Generic;

public class WeatherStation
{
    private Reading reading;
    private readonly List<DateTime> recordDates = new();
    private readonly List<decimal> temperatures = new();

    public void AcceptReading(Reading reading)
    {
        this.reading = reading;
        recordDates.Add(DateTime.Now);
        temperatures.Add(reading.Temperature);
    }

    public void ClearAll()
    {
        reading = new Reading();
        recordDates.Clear();
        temperatures.Clear();
    }

    public decimal LatestTemperature
    {
        get { return reading.Temperature; }
    }

    public decimal LatestPressure
    {
        get { return reading.Pressure; }
    }

    public decimal LatestRainfall
    {
        get { return reading.Rainfall; }
    }

    public bool HasHistory
    {
        get { return recordDates.Count > 1; }
    }

    public Outlook ShortTermOutlook
    {
        get
        {
            return reading.Equals(new Reading()) ? throw new ArgumentException() : reading.Temperature switch
            {
                < 30m when reading.Pressure < 10m => Outlook.Cool,
                > 50m => Outlook.Good,
                _ => Outlook.Warm
            };
        }
    }

    public Outlook LongTermOutlook
    {
        get
        {
            return reading.WindDirection switch
            {
                WindDirection.Southerly => Outlook.Good,
                WindDirection.Easterly => reading.Temperature > 20 ? Outlook.Good : Outlook.Warm,
                WindDirection.Northerly => Outlook.Cool,
                WindDirection.Westerly => Outlook.Rainy,
                _ => throw new ArgumentException()
            };
        }
    }

    public State RunSelfTest() => reading.Equals(new Reading()) ? State.Bad : State.Good;
}

/*** Please do not modify this struct ***/
public struct Reading
{
    public decimal Temperature { get; }
    public decimal Pressure { get; }
    public decimal Rainfall { get; }
    public WindDirection WindDirection { get; }

    public Reading(decimal temperature, decimal pressure,
        decimal rainfall, WindDirection windDirection)
    {
        Temperature = temperature;
        Pressure = pressure;
        Rainfall = rainfall;
        WindDirection = windDirection;
    }
}

/*** Please do not modify this enum ***/
public enum State
{
    Good,
    Bad
}

/*** Please do not modify this enum ***/
public enum Outlook
{
    Cool,
    Rainy,
    Warm,
    Good
}

/*** Please do not modify this enum ***/
public enum WindDirection
{
    Unknown, // default
    Northerly,
    Easterly,
    Southerly,
    Westerly
}
