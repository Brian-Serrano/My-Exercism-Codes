using System.Text.RegularExpressions;

public static class Alphametics
{
    public static IDictionary<char, int> Solve(string equation)
    {
        List<char> letters = equation.Where(x => char.IsLetter(x)).Distinct().ToList();
        List<string> words = Regex.Split(equation, "\\s?\\+\\s?|\\s==\\s").ToList();
        List<char> firstLetters = words.Select(x => x[0]).ToList();
        IDictionary<char, int> result = Backtrack(new Dictionary<char, int>(), letters, words, firstLetters);
        if (result.Count > 0)
        {
            return result;
        }
        else
        {
            throw new ArgumentException();
        }
    }

    private static IDictionary<char, int> Backtrack(Dictionary<char, int> mapping, List<char> letters, List<string> words, List<char> firstLetters)
    {
        if (letters.Count == 0)
        {
            return mapping;
        }

        char letter = letters[0];
        letters.RemoveAt(0);

        for (int i = 0; i < 10; i++)
        {
            if (mapping.ContainsValue(i))
            {
                continue;
            }
            if (i == 0 && firstLetters.Contains(letter))
            {
                continue;
            }
            mapping[letter] = i;
            var newMapping = Backtrack(mapping, letters, words, firstLetters);
            if (newMapping.Count > 0 && IsValid(mapping, words))
            {
                return newMapping;
            }
            mapping.Remove(letter);
        }

        letters.Insert(0, letter);
        return new Dictionary<char, int>();
    }

    private static bool IsValid(Dictionary<char, int> mapping, List<string> words)
    {
        List<long> numbers = words.Select(w => long.Parse(string.Join("", w.Select(c => mapping[c])))).ToList();
        return numbers[..^1].Sum() == numbers[^1];
    }
}