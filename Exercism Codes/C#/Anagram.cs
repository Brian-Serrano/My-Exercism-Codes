public class Anagram
{
    public string Word { get; }

    public Anagram(string baseWord)
    {
        Word = baseWord;
    }

    public string[] FindAnagrams(string[] potentialMatches)
    {
        return potentialMatches.Where(c => c.ToLower().Order().SequenceEqual(Word.ToLower().Order()) && Word.ToLower() != c.ToLower()).ToArray();
    }
}