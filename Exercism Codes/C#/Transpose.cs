using System.Text;

public static class Transpose
{
    public static string String(string input)
    {
        string[] st = PadStringArray(input.Split("\n"));
        List<StringBuilder> sb = Enumerable.Range(0, st[0].Length)
            .Select(_ => new StringBuilder()).ToList();
        foreach (string s in st)
        {
            for (int j = 0; j < s.Length; j++)
            {
                sb[j].Append(s[j]);
            }
        }
        return string.Join("\n", sb);
    }

    private static string[] PadStringArray(string[] input)
    {
        for (int i = input.Length - 1; i > 0; i--)
        {
            if (input[i].Length > input[i - 1].Length)
            {
                int rep = input[i].Length - input[i - 1].Length;
                input[i - 1] += string.Join("", Enumerable.Repeat(" ", rep));
            }
        }

        return input;
    }
}