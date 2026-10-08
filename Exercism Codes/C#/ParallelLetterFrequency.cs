public static class ParallelLetterFrequency
{
    public static Task<Dictionary<char, int>> Calculate(IEnumerable<string> texts)
    {
        List<char> input = string.Join("", texts).ToLower()
            .Where(c => !char.IsDigit(c) && !char.IsPunctuation(c) && !char.IsWhiteSpace(c) && !char.IsControl(c))
            .ToList();

        Dictionary<char, int> letterFrequency = new Dictionary<char, int>();
        foreach (char letter in input)
        {
            if (letterFrequency.ContainsKey(letter))
                letterFrequency[letter]++;
            else
                letterFrequency[letter] = 1;
        }
        return Task.FromResult(letterFrequency);
    }
}