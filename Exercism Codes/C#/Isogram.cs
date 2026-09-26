using System;
using System.Linq;

public static class Isogram
{
    public static bool IsIsogram(string word)
    {
        string newWord = word.Replace(" ", "").Replace("-", "").ToLower();
        return newWord.Length == string.Join("", newWord.Distinct()).Length;
    }
}
