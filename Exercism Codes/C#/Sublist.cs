public enum SublistType
{
    Equal,
    Unequal,
    Superlist,
    Sublist
}

public static class Sublist
{
    public static SublistType Classify<T>(List<T> list1, List<T> list2)
        where T : IComparable
    {
        if (list1.SequenceEqual(list2))
        {
            return SublistType.Equal;
        }
        if (list1.Count > list2.Count && SubList(list1, list2))
        {
            return SublistType.Superlist;
        }
        if (list1.Count < list2.Count && SubList(list2, list1))
        {
            return SublistType.Sublist;
        }
        return SublistType.Unequal;
    }

    private static bool SubList<T>(List<T> list1, List<T> list2)
    {
        for (int i = 0; i <= list1.Count - list2.Count; i++)
        {
            if (list1.Skip(i).Take(list2.Count).SequenceEqual(list2))
            {
                return true;
            }
        }
        return false;
    }
}