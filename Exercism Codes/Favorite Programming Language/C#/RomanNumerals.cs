using System;
using System.Collections.Generic;
using System.Text;

public static class RomanNumeralExtension
{
    public static string ToRoman(this int value)
    {
        return GetRomanRecursively(value, new StringBuilder());
    }

    private static string GetRomanRecursively(int number, StringBuilder romanNumeral)
    {
        List<Symbols> symbols = new List<Symbols>()
        {
            new Symbols(1000, "M"),
            new Symbols(900, "CM"),
            new Symbols(500, "D"),
            new Symbols(400, "CD"),
            new Symbols(100, "C"),
            new Symbols(90, "XC"),
            new Symbols(50, "L"),
            new Symbols(40, "XL"),
            new Symbols(10, "X"),
            new Symbols(9, "IX"),
            new Symbols(5, "V"),
            new Symbols(4, "IV"),
            new Symbols(1, "I")
        };

        foreach (Symbols symbol in symbols)
        {
            if (number >= symbol.number)
            {
                romanNumeral.Append(symbol.symbol);
                number -= symbol.number;
                return GetRomanRecursively(number, romanNumeral);
            }
        }

        return romanNumeral.ToString();
    }

    private class Symbols
    {
        public int number;
        public string symbol;
        public Symbols(int n, string s)
        {
            number = n;
            symbol = s;
        }
    }
}