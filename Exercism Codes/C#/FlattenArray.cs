public static class FlattenArray
{
    public static IEnumerable<object?> Flatten(IEnumerable<object?> input)
    {
        List<object?> result = new List<object?>();
        foreach (object? c in input)
        {
            if (c == null) continue;
            else if (c is IEnumerable<object?> e) result.AddRange(Flatten(e));
            else if (c is object i) result.Add(i);
        }
        return result;
    }
}