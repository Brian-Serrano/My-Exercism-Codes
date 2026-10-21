public static class Strain
{
    public static IEnumerable<T> Keep<T>(this IEnumerable<T> collection, Func<T, bool> predicate)
    {
        List<T> result = new List<T>();
        foreach (T c in collection)
            if (predicate.Invoke(c))
                result.Add(c);
        return result;
    }

    public static IEnumerable<T> Discard<T>(this IEnumerable<T> collection, Func<T, bool> predicate)
    {
        List<T> result = new List<T>();
        foreach (T c in collection)
            if (!predicate.Invoke(c))
                result.Add(c);
        return result;
    }
}