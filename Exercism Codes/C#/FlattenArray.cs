using System;
using System.Collections;
using System.Collections.Generic;

public static class FlattenArray
{
    public static IEnumerable<int> Flatten(IEnumerable input)
    {
        List<int> result = new List<int>();
        foreach (object c in input)
        {
            if (c == null) continue;
            if (c is IEnumerable e) result.AddRange(Flatten(e));
            if (c is int i) result.Add(i);
        }
        return result;
    }
}