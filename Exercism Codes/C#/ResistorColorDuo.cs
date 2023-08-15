using System;

public static class ResistorColorDuo
{
    private static string[] colorsList = new string[]
    {
        "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"
    };

    public static int Value(string[] colors)
    {
        return int.Parse($"{Array.IndexOf(colorsList, colors[0])}{Array.IndexOf(colorsList, colors[1])}");
    }
}
