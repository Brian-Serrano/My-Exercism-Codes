public static class ResistorColorTrio
{
    private static readonly List<string> COLORS = [
        "black", "brown", "red", "orange", "yellow", 
        "green", "blue", "violet", "grey", "white"
    ];

    public static string Label(string[] colors)
    {
        string str = COLORS.IndexOf(colors[0]) + "" + COLORS.IndexOf(colors[1]) + new string('0', COLORS.IndexOf(colors[2]));

        long value = long.Parse(str);

        if (value < 1000)
        {
            return $"{value} ohms";
        }
        else if (value < 1_000_000)
        {
            return $"{value / 1000} kiloohms";
        }
        else if (value < 1_000_000_000)
        {
            return $"{value / 1_000_000} megaohms";
        }
        else
        {
            return $"{value / 1_000_000_000} gigaohms";
        }
    }
}
