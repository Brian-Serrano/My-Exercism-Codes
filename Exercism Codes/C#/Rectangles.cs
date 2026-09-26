using System;
using System.Text.RegularExpressions;

public static class Rectangles
{
    private static readonly Regex externalRegex = new(@"^\+[+-]*\+$");
    private static readonly Regex internalRegex = new(@"^[+|].*[+|]$");

    public static int Count(string[] rows)
    {
        int count = 0;
        for(int i = 0; i < rows.Length; i++)
        {
            for(int j = 0; j < rows[i].Length; j++)
            {
                if (rows[i][j] == '+')
                {
                    for(int k = j + 1; k < rows[i].Length; k++)
                    {
                        if (externalRegex.IsMatch(rows[i].AsSpan(j, (k + 1) - j)))
                        {
                            for(int l = i + 1; l < rows.Length; l++)
                            {
                                if (internalRegex.IsMatch(rows[l].AsSpan(j, (k + 1) - j)))
                                {
                                    if (externalRegex.IsMatch(rows[l].AsSpan(j, (k + 1) - j))) count++;
                                }
                                else break;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}