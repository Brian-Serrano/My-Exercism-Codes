using System;
using System.Linq;

public static class ResistorColor
{
    public static int ColorCode(string color)
    {
        return Colors().ToList().IndexOf(color);
    }

    public static string[] Colors()
    {
        return new string[] { "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white" };
    }
}