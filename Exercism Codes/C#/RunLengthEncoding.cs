using System.Text;
using System.Text.RegularExpressions;

public static class RunLengthEncoding
{
    public static string Encode(string input)
    {
        int count = 1;
        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= input.Length; i++)
        {
            if (i < input.Length && input[i] == input[i - 1])
            {
                count++;
            }
            else
            {
                output.Append(count < 2 ? $"{input[i - 1]}" : $"{count}{input[i - 1]}");
                count = 1;
            }
        }

        return output.ToString();
    }

    public static string Decode(string input)
    {
        MatchCollection matches = Regex.Matches(input, "(\\d*[A-Z a-z])");
        StringBuilder output = new StringBuilder();

        foreach (Match match in matches)
        {
            string str = match.Value;
            char letter = str[^1];
            string number = str[..^1];
            output.Append(new string(letter, string.IsNullOrEmpty(number) ? 1 : int.Parse(number)));
        }

        return output.ToString();
    }
}
