public static class LineUp
{
    public static string Format(string name, int number)
    {
        string ending = "th";
        string n = number.ToString();

        if (n[^1] == '1' && !(n.Length > 1 && n[^2] == '1'))
        {
            ending = "st";
        }
        if (n[^1] == '2' && !(n.Length > 1 && n[^2] == '1'))
        {
            ending = "nd";
        }
        if (n[^1] == '3' && !(n.Length > 1 && n[^2] == '1'))
        {
            ending = "rd";
        }
        return $"{name}, you are the {n}{ending} customer we serve today. Thank you!";
    }
}
