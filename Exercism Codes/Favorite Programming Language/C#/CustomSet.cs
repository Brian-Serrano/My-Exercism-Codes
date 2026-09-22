
public class CustomSet
{
    private HashSet<int> values;

    public CustomSet(int[] values = null) => this.values = new HashSet<int>(values ?? []);

    public CustomSet Add(int value) => new(values.Append(value).ToArray());

    public bool Empty() => values.Count == 0;

    public bool Contains(int value) => values.Contains(value);

    public bool Subset(CustomSet right) => values.All(right.Contains);

    public bool Disjoint(CustomSet right) => !values.Any(right.Contains);

    public CustomSet Intersection(CustomSet right) => new(values.Intersect(right.values).ToArray());

    public CustomSet Difference(CustomSet right) => new(values.Except(right.values).ToArray());

    public CustomSet Union(CustomSet right) => new(values.Union(right.values).ToArray());

    public override bool Equals(object? obj) => obj is not CustomSet other ? false : other.values.SetEquals(this.values);

    public override string ToString() => string.Join(", ", values);
}