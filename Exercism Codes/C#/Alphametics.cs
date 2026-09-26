public static class Alphametics
{
    public static IEnumerable<IEnumerable<T>> Permutations<T>(IEnumerable<T> source, int r = -1)
    {
        T[] items = source.ToArray();
        int n = items.Length;

        if (r == -1)
            r = n;

        bool[] used = new bool[n];
        List<T> result = new List<T>(r);

        IEnumerable<IEnumerable<T>> Backtrack()
        {
            if (result.Count == r)
            {
                yield return result.ToArray();
                yield break;
            }

            for (int i = 0; i < n; i++)
            {
                if (used[i]) continue;

                used[i] = true;
                result.Add(items[i]);

                foreach (var perm in Backtrack())
                    yield return perm;

                result.RemoveAt(result.Count - 1);
                used[i] = false;
            }
        }

        return Backtrack();
    }

    public static IDictionary<char, int> Solve(string equation)
    {
        HashSet<char> letters = new HashSet<char>();
        HashSet<char> beginningLetters = new HashSet<char>();
        for (int i = 0; i < equation.Length; i++)
        {
            if (char.IsLetter(equation[i]))
            {
                letters.Add(equation[i]);
                if (i == 0 || equation[i - 1] == '+' || equation[i - 1] == ' ')
                {
                    beginningLetters.Add(equation[i]);
                }
            }
        }

        foreach (var perm in Permutations(Enumerable.Range(0, 10), letters.Count))
        {
            var mapping = new Dictionary<char, int>();
            bool valid = true;
            for (int i = 0; i < letters.Count; i++)
            {
                if (beginningLetters.Contains(letters.ElementAt(i)) && perm.ElementAt(i) == 0)
                {
                    valid = false;
                    break;
                }
                mapping[letters.ElementAt(i)] = perm.ElementAt(i);
            }
            if (valid)
            {
                string mapped = string.Join("", equation.Select(c => mapping.Keys.Contains(c) ? mapping[c].ToString()[0] : c));
                string[] parts = mapped.Split([" == ", " + ", "+ ", " +"], StringSplitOptions.None);
                IEnumerable<string> leftSide = parts.Take(parts.Length - 1);
                long rightSide = long.Parse(parts.Last());
                if (leftSide.Sum(part => long.Parse(part)) == rightSide)
                {
                    return mapping;
                }
            }
        }
        throw new ArgumentException();
    }
}