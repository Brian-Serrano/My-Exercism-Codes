using System.Text;
using System.Text.RegularExpressions;

public static class OcrNumbers
{
    public static string Convert(string input)
    {
        string[] text = input.Split("\n");

        if (text.Length % 4 != 0)
            throw new ArgumentException();
        if (text[0].Length % 3 != 0)
            throw new ArgumentException();

        var readers = new List<Predicate<List<string>>>
        {
            t => t.SequenceEqual(new List<string> { " _ ", "| |", "|_|", "   " }),
            t => t.SequenceEqual(new List<string> { "   ", "  |", "  |", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", " _|", "|_ ", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", " _|", " _|", "   " }),
            t => t.SequenceEqual(new List<string> { "   ", "|_|", "  |", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", "|_ ", " _|", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", "|_ ", "|_|", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", "  |", "  |", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", "|_|", "|_|", "   " }),
            t => t.SequenceEqual(new List<string> { " _ ", "|_|", " _|", "   " })
        };

        List<List<List<string>>> cropped = Crop(text.ToList());
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < cropped.Count; j++)
        {
            for (int k = 0; k < cropped[j].Count; k++)
            {
                for (int i = 0; i <= readers.Count; i++)
                {
                    if (i == readers.Count)
                        sb.Append("?");
                    else if (readers[i](cropped[j][k]))
                    {
                        sb.Append(i);
                        break;
                    }
                }
            }
            if (j != cropped.Count - 1)
                sb.Append(",");
        }
        return sb.ToString();
    }

    private static List<List<List<string>>> Crop(List<string> text)
    {
        List<List<string>> result = new List<List<string>>();
        for (int i = 0; i < text.Count; i += 4)
        {
            result.Add(text.GetRange(i, 4));
        }
        return result.Select(r => Zip(r.Select(s => Regex.Matches(s, ".{3}").Select(c => c.Value).ToList()).ToList())).ToList();
    }

    private static List<List<string>> Zip(List<List<string>> input)
    {
        return Enumerable.Range(0, input[0].Count)
            .Select(i => input.Select(row => row[i]).ToList())
            .ToList();
    }

}