using System;
using System.Collections.Generic;
public static class NucleotideCount
{
    public static IDictionary<char, int> Count(string sequence)
    {
        IDictionary<char, int> res = new Dictionary<char, int> {
            ['A'] = 0, ['C'] = 0, ['G'] = 0, ['T'] = 0
        };
        foreach(char s in sequence)
        {
            if (!res.ContainsKey(s)) throw new ArgumentException();
            res[s]++;
        }
        return res;
    }
}