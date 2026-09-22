using System;
using System.Text;

public static class BeerSong
{
    public static string Recite(int startBottles, int takeDown)
    {
        StringBuilder song = new StringBuilder();
        for (int i = startBottles, j = 0; j < takeDown; i--, j++)
        {
            song.Append((i == 0 ? "No more" : i) + (i == 1 ? " bottle" : " bottles") + " of beer on the wall, " + (i == 0 ? "no more" : i) + (i == 1 ? " bottle" : " bottles") + " of beer.\n" +
                    (i == 0 ? "Go to the store" : "Take " + (i == 1 ? "it" : "one") + " down") + " and " + (i == 0 ? "buy some more, 99" : "pass it around, " + (i == 1 ? "no more" : i - 1)) + (i == 2 ? " bottle" : " bottles") + " of beer on the wall.\n\n");
        }
        return song.ToString().Trim();
    }
}