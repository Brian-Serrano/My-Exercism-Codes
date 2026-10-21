public static class KillerSudokuHelper
{
    public static IEnumerable<int[]> Combinations(int sum, int size, int[] exclude)
    {
        return GetCombinations(Enumerable.Range(1, 9).ToList(), size)
            .Where(i => i.Sum() == sum && !i.Any(x => exclude.Contains(x)))
            .Select(i => i.ToArray());
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
