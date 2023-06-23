using System;

public static class Darts
{
    public static int Score(double x, double y)
    {
        double distance = Math.Sqrt(Math.Pow(x, 2) + Math.Pow(y, 2));
        if (distance > 10) return 0;
        else if (distance > 5) return 1;
        else if (distance > 1) return 5;
        else return 10;
    }
}