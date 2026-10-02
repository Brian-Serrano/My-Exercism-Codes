using System.Text.RegularExpressions;

public static class CryptoSquare
{
    public static string Ciphertext(string plaintext)
    {
        string normalized = Regex.Replace(plaintext.ToLower(), @"\W", "");
        int len = normalized.Length;
        int c = (int)Math.Ceiling(Math.Sqrt(len));
        int r = (int)Math.Floor(Math.Sqrt(len));
        if (c * r > len)
            normalized += string.Join("", Enumerable.Repeat(' ', r - (len % r)));
        if (c * r < len)
        {
            normalized += string.Join("", Enumerable.Repeat(' ', c - (len % c)));
            r++;
        }
        List<string> rect = Regex.Matches(normalized, ".{" + c + "}").Select(m => m.Value).ToList();
        return string.Join(" ", Enumerable.Range(0, rect[0].Length)
            .Select(idx => string.Join("", rect.Select(lst => lst[idx]))));
    }
}
