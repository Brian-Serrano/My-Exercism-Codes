public static class ListOps
{
    public static int Length<T>(List<T> input)
    {
        return input.Count;
    }

    public static List<T> Reverse<T>(List<T> input)
    {
        return input.Reverse<T>().ToList();
    }

    public static List<TOut> Map<TIn, TOut>(List<TIn> input, Func<TIn, TOut> map)
    {
        return input.Select(map).ToList();
    }

    public static List<T> Filter<T>(List<T> input, Func<T, bool> predicate)
    {
        return input.Where(predicate).ToList();
    }

    public static TOut Foldl<TIn, TOut>(List<TIn> input, TOut start, Func<TOut, TIn, TOut> func)
    {
        return input.Aggregate(start, func);
    }

    public static TOut Foldr<TIn, TOut>(List<TIn> input, TOut start, Func<TIn, TOut, TOut> func)
    {
        for (int i = input.Count - 1; i >= 0; i--)
        {
            start = func(input[i], start);
        }
        return start;
    }

    public static List<T> Concat<T>(List<List<T>> input)
    {
        return input.SelectMany(x => x).ToList();
    }

    public static List<T> Append<T>(List<T> left, List<T> right)
    {
        return left.Concat(right).ToList();
    }
}