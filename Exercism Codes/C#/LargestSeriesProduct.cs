using System.Text.RegularExpressions;

public static class LargestSeriesProduct
{
    public static long GetLargestProduct(string digits, int span) 
    {
        if (Regex.IsMatch(digits, @"\D"))
        {
            throw new ArgumentException("Input string must contain only digits.");
        }
        if (span < 0 || span > digits.Length)
        {
            throw new ArgumentException("Span must be non-negative and less than or equal to the length of the input string.");
        }

        List<string> subNumbers = new List<string>();
        for (int i = 0; i + span <= digits.Length; i++)
            subNumbers.Add(digits.Substring(i, span));

        return subNumbers.Select(CalculateDigits).Max();
    }

    private static long CalculateDigits(string subnumber)
    {
        return subnumber.Select(c => int.Parse(c.ToString())).Aggregate(1L, (acc, next) => acc * next);
    }
}