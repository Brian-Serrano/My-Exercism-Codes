using System;

public static class LogAnalysis 
{
    public static string SubstringAfter(this string message, string delimiter)
    {
        return message.Split(delimiter)[1];
    }

    public static string SubstringBetween(this string message, string first, string last)
    {
        return message[(first.Length + message.IndexOf(first))..message.IndexOf(last)];
    }

    public static string Message(this string message)
    {
        return message.SubstringAfter(": ");
    }

    public static string LogLevel(this string message)
    {
        return message.SubstringBetween("[", "]");
    }
}