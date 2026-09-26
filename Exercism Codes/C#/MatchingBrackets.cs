using System;
using System.Text.RegularExpressions;
public static class MatchingBrackets
{
    public static bool IsPaired(string input)
    {
        string myRegex = "(\\{[^\\{\\}\\[\\]\\(\\)]*\\})|(\\([^\\{\\}\\[\\]\\(\\)]*\\))|(\\[[^\\{\\}\\[\\]\\(\\)]*\\])";
        while(Regex.IsMatch(input, myRegex))
        {
            input = Regex.Replace(input, myRegex, "");
        }
        return Regex.Replace(input, "[^\\{\\}\\[\\]\\(\\)]", "").Length == 0;
    }
}
