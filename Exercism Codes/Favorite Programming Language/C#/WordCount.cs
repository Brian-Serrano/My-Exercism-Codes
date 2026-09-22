using System.Text.RegularExpressions;

public static class WordCount
{
    public static IDictionary<string, int> CountWords(string phrase)
    {
        string[] arr = Regex.Split(phrase.ToLower().Trim(), "[^a-zA-Z0-9']+");
        IDictionary<string, int> result = new Dictionary<string, int>();
        foreach (string word in arr)
        {
            string w = Regex.Replace(word, "^'{1,2}|'{1,2}$", "");
            if (w.Length > 0)
            {
                if (result.ContainsKey(w))
                {
                    result[w]++;
                }
                else
                {
                    result[w] = 1;
                }
            }
        }

        return result;
    }
}