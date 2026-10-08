public static class Change
{
    public static int[] FindFewestCoins(int[] coins, int target)
    {
        if (target == 0)
            return [];
        if (target < 0)
            throw new ArgumentException();

        var possibleCoins = coins.Select(x => new List<int> { x }).ToList();

        while (!possibleCoins.Any(c => c.Sum() == target))
        {
            possibleCoins = possibleCoins.SelectMany(c => Compute(c, coins))
                .Distinct(new ListComparer<int>()).ToList();

            if (possibleCoins.All(c => c.Sum() > target))
                throw new ArgumentException();
        }

        return possibleCoins.First(c => c.Sum() == target).ToArray();
    }

    private static List<List<int>> Compute(List<int> coin, int[] coins)
    {
        List<List<int>> result = new List<List<int>>();
        foreach (var c in coins)
        {
            List<int> res = [.. coin];
            res.Add(c);
            result.Add(res.Order().ToList());
        }
        return result;
    }
}

class ListComparer<T> : IEqualityComparer<List<T>>
{
    public bool Equals(List<T>? x, List<T>? y)
    {
        return x != null && y != null && x.SequenceEqual(y);
    }

    public int GetHashCode(List<T> obj)
    {
        return obj.Aggregate(17, (hash, item) =>
            HashCode.Combine(hash, item));
    }
}