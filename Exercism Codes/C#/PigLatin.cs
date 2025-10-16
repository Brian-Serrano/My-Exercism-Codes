public static class PigLatin
{
    public static string Translate(string word)
    {
        List<string> result = [];

        foreach (string c in word.Split(" "))
        {
            int sliceIdx = 0;
            while (true)
            {
                bool isVowel = "aeiou".Contains(c[sliceIdx]);
                bool rule1 = sliceIdx + 2 <= c.Length && (c.Substring(sliceIdx, 2) == "yt" || c.Substring(sliceIdx, 2) == "xr");
                bool rule4 = (sliceIdx - 1 >= 0 && !"aeiou".Contains(c[sliceIdx - 1])) && c[sliceIdx] == 'y';

                if (isVowel || rule1 || rule4)
                {
                    result.Add(c[sliceIdx..] + c[..sliceIdx] + "ay");
                    break;
                }
                if (c.Substring(sliceIdx, 2) == "qu")
                {
                    sliceIdx++;
                }
                sliceIdx++;
            }
        }

        return string.Join(" ", result);
    }
}