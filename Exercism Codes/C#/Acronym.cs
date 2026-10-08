using System.Text.RegularExpressions;

public static class Acronym
{
    public static string Abbreviate(string phrase)
    {
        string[] lst = new Regex(" +").Split(phrase.Replace("-", " ").Replace("_", " "));
        return string.Join("", lst.Select(x => x[0])).ToUpper();
    }
}