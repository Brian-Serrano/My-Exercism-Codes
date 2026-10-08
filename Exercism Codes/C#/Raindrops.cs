public static class Raindrops
{
    public static string Convert(int number)
    {
        string pl = "";
        if (number % 3 == 0)
            pl += "Pling";
        if (number % 5 == 0)
            pl += "Plang";
        if (number % 7 == 0)
            pl += "Plong";

        return string.IsNullOrEmpty(pl) ? number.ToString() : pl;
    }
}