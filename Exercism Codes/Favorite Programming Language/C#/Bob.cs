using System;
using System.Text.RegularExpressions;
public static class Bob
{
    public static string Response(string statement)
    {
        bool endsWithQuestionMark = statement.Trim().EndsWith("?");
        bool isUppercase = statement.Equals(statement.ToUpper()) && Regex.IsMatch(statement, @"[a-zA-Z]");
        if (string.IsNullOrWhiteSpace(statement))
        {
            return "Fine. Be that way!";
        }
        else if (endsWithQuestionMark && isUppercase)
        {
            return "Calm down, I know what I'm doing!";
        }
        else if (isUppercase)
        {
            return "Whoa, chill out!";
        }
        else if (endsWithQuestionMark)
        {
            return "Sure.";
        }
        else
        {
            return "Whatever.";
        }
    }
}