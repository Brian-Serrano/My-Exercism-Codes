public static class AtbashCipher
{
    public static string Encode(string plainValue)
    {
        List<char> result = [];
        foreach (char x in plainValue)
        {
            if (char.IsLetterOrDigit(x))
            {
                result.Add(Transform(x));

                if ((result.Count + 1) % 6 == 0)
                {
                    result.Add(' ');
                }
            }
        }
        return string.Join("", result).Trim();
    }

    public static string Decode(string encodedValue) => string.Join("", encodedValue.Where(x => char.IsLetterOrDigit(x)).Select(Transform));

    private static char Transform(char x) => char.IsLetter(x) ? (char)((26 - (char.ToLower(x) - 97) - 1) + 97) : x;
}
