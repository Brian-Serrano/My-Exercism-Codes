using System;

public class SpaceAge
{
    private int seconds;

    public SpaceAge(int seconds)
    {
        this.seconds = seconds;
    }

    public double OnEarth()
    {
        return seconds / (1.0 * 31557600);
    }

    public double OnMercury()
    {
        return seconds / (0.2408467 * 31557600);
    }

    public double OnVenus()
    {
        return seconds / (0.61519726 * 31557600);
    }

    public double OnMars()
    {
        return seconds / (1.8808158 * 31557600);
    }

    public double OnJupiter()
    {
        return seconds / (11.862615 * 31557600);
    }

    public double OnSaturn()
    {
        return seconds / (29.447498 * 31557600);
    }

    public double OnUranus()
    {
        return seconds / (84.016846 * 31557600);
    }

    public double OnNeptune()
    {
        return seconds / (164.79132 * 31557600);
    }
}