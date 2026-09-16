public static class BookStore
{
    public static decimal Total(IEnumerable<int> books)
    {
        IDictionary<int, int> cnt = GetFrequency(books);
        int freq = cnt.Values.DefaultIfEmpty(0).Max();
        List<List<int>> combinations = FindCombinations(
            cnt, freq, cnt.Keys.ToList(), 0, new List<List<int>> { Enumerable.Repeat(0, freq).ToList() }
            );
        IDictionary<int, decimal> discounts = new Dictionary<int, decimal>
        {
            { 1, 0.0m },
            { 2, 0.4m },
            { 3, 0.8m },
            { 4, 1.6m },
            { 5, 2.0m }
        };
        return combinations.Select(lst => lst.Select(books => books * (8.0m - discounts[books])).Sum()).Min();
    }

    private static IDictionary<int, int> GetFrequency(IEnumerable<int> books)
    {
        Dictionary<int, int> frequency = new Dictionary<int, int>();

        foreach (int x in books)
        {
            if (frequency.ContainsKey(x))
            {
                frequency[x]++;
            }
            else
            {
                frequency[x] = 1;
            }
        }

        return frequency;
    }

    private static List<List<int>> FindCombinations(IDictionary<int, int> cnt, int freq, List<int> nums, int num, List<List<int>> arr)
    {
        if (num == nums.Count)
        {
            return arr;
        }
        else
        {
            List<List<int>> arr2 = new List<List<int>>(arr);
            arr = new List<List<int>>();

            foreach (List<int> x in arr2)
            {
                foreach (List<int> positions in GetCombinations(Enumerable.Range(0, freq).ToList(), cnt[nums[num]]))
                {
                    List<int> newCombination = new List<int>(x);
                    foreach (int pos in positions)
                    {
                        newCombination[pos] += 1;
                    }
                    arr.Add(newCombination);
                }
            }

            return FindCombinations(cnt, freq, nums, num + 1, arr);
        }
    }

    private static List<List<int>> GetCombinations(List<int> arr, int r)
    {
        List<List<int>> result = new List<List<int>>();

        void Backtrack(int start, List<int> current)
        {
            if (current.Count == r)
            {
                result.Add(new List<int>(current));
                return;
            }
            for (int i = start; i < arr.Count; i++)
            {
                current.Add(arr[i]);
                Backtrack(i + 1, current);
                current.RemoveAt(current.Count - 1);
            }
        }

        Backtrack(0, new List<int>());
        return result;
    }
}