using System.Text.RegularExpressions;

public static class AffineCipher
{
    public static string Encode(string plainText, int a, int b)
    {
        if (a % 2 == 0 || a % 13 == 0)
        {
            throw new ArgumentException();
        }
        string enc = string.Join("", Regex.Replace(plainText.ToLower(), "\\W", "")
            .Select(x => x >= 48 && x <= 57 ? x - 'a' : ((a * (x - 'a')) + b) % 26)
            .Select(x => (char)(x + 'a')));
        return string.Join(" ", Regex.Matches(enc, ".{1,5}").Select(m => m.Value));
    }

    public static string Decode(string cipheredText, int a, int b)
    {
        if (a % 2 == 0 || a % 13 == 0)
        {
            throw new ArgumentException();
        }
        return string.Join("", Regex.Replace(cipheredText.ToLower(), "\\W", "")
            .Select(x => x >= 48 && x <= 57 ? x : (Mmi(a) * ((x - 'a') - b)) % 26)
            .Select(x => (char)(x >= 48 && x <= 57 ? x : ((x + 26) % 26) + 'a')));
    }

    private static int Mmi(int a)
    {
        for (int i = 1; i < 26; i++)
        {
            if (a * i % 26 == 1)
            {
                return i;
            }
        }
        return -1;
    }
}
