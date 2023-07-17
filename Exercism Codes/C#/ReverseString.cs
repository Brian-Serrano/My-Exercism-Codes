using System;
using System.Linq;
using System.Text;

public static class ReverseString
{
    public static string Reverse(string input) => string.Join("", input.Reverse().ToList());
}