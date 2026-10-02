using System.Text.RegularExpressions;

public static class Grep
{
    public static string Match(string pattern, string flags, string[] files)
    {
        string[] flgs = flags.Split(" ");
        List<(string, string)> lst = new List<(string, string)>();

        foreach (string fileName in files)
        {
            int lineNumber = 0;
            string[] lines = File.ReadAllLines(fileName);
            foreach (string line in lines)
            {
                lineNumber += 1;
                string pat = flgs.Contains("-x") ? "^" + pattern + "$" : pattern;
                RegexOptions patternFlag = flgs.Contains("-i") ? RegexOptions.IgnoreCase : RegexOptions.None;
                bool match = Regex.IsMatch(line, pat, patternFlag);
                if (flgs.Contains("-v") ? !match : match)
                {
                    string number = flgs.Contains("-n") ? lineNumber + ":" : "";
                    lst.Add((fileName, number + line));
                }
            }
        }

        if (flgs.Contains("-l"))
        {
            return string.Join("\n", lst.Select(c => c.Item1).Distinct());
        }

        return string.Join("\n", lst.Select(c => files.Length > 1 ? c.Item1 + ":" + c.Item2 : c.Item2));
    }
}