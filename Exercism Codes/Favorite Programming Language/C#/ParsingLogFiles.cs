using System;
using System.Linq;
using System.Text.RegularExpressions;

public class LogParser
{
    public bool IsValidLine(string text)
    {
        return new Regex(@"^(?:\[(?:TRC|DBG|INF|WRN|ERR|FTL)\]).+").IsMatch(text);
    }

    public string[] SplitLogLine(string text)
    {
        return new Regex(@"<[-*=\^]+>").Split(text);
    }

    public int CountQuotedPasswords(string lines)
    {
        return new Regex(@""".*password.*""", RegexOptions.IgnoreCase).Count(lines);
    }

    public string RemoveEndOfLineText(string line)
    {
        return new Regex(@"end-of-line\d*").Replace(line, "");
    }

    public string[] ListLinesWithPasswords(string[] lines)
    {
        return lines.Select(l => {
            Match matcher = new Regex(@"password[\w\d]+", RegexOptions.IgnoreCase).Match(l);
            return (matcher.Success ? matcher.Value : "--------") + ": " + l;
        }).ToArray();
    }
}
