using System.Collections;

public class Graph : IEnumerable<object>
{
    private readonly List<object> items = new();

    public List<Node> Nodes
    {
        get
        {
            return GetItems<Node>();
        }
    }

    public List<Edge> Edges
    {
        get
        {
            return GetItems<Edge>();
        }
    }

    public List<Attr> Attrs
    {
        get
        {
            return GetItems<Attr>();
        }
    }

    private List<T> GetItems<T>()
    {

        List<T> nodes = new List<T>();
        foreach (object item in items)
        {
            if (item is T node)
            {
                nodes.Add(node);
            }
        }
        return nodes;
    }

    public void Add(string key, string value)
    {
        items.Add(new Attr(key, value));
    }

    public void Add(Node node)
    {
        items.Add(node);
    }

    public void Add(Edge edge)
    {
        items.Add(edge);
    }

    public IEnumerator<object> GetEnumerator()
    {
        return items.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }

    public override bool Equals(object? obj)
    {
        if (obj is not Graph other)
            return false;

        if (Nodes.Count != other.Nodes.Count)
            return false;

        if (Edges.Count != other.Edges.Count)
            return false;

        if (Attrs.Count != other.Attrs.Count)
            return false;

        for (int i = 0; i < Nodes.Count; i++)
        {
            if (!Nodes[i].Equals(other.Nodes[i]))
                return false;
        }

        for (int i = 0; i < Edges.Count; i++)
        {
            if (!Edges[i].Equals(other.Edges[i]))
                return false;
        }

        for (int i = 0; i < Attrs.Count; i++)
        {
            if (!Attrs[i].Equals(other.Attrs[i]))
                return false;
        }

        return true;
    }
    public override int GetHashCode()
    {
        int hash = 17;

        foreach (var node in Nodes)
        {
            hash = hash * 31 + (node?.GetHashCode() ?? 0);
        }

        foreach (var edge in Edges)
        {
            hash = hash * 31 + (edge?.GetHashCode() ?? 0);
        }

        foreach (var attr in Attrs)
        {
            hash = hash * 31 + (attr?.GetHashCode() ?? 0);
        }

        return hash;
    }
    public override string? ToString() => $"[Nodes: {string.Join(", ", Nodes)}, Edges: {string.Join(", ", Edges)}, Attrs: {string.Join(", ", Attrs)}]";
}

public class Node : IEnumerable<KeyValuePair<string, string>>
{
    public string Name { get; }

    private readonly Dictionary<string, string> attributes = new();

    public Node(string name)
    {
        Name = name;
    }

    public void Add(string key, string value)
    {
        attributes[key] = value;
    }

    public IEnumerator<KeyValuePair<string, string>> GetEnumerator()
    {
        return attributes.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }

    public override bool Equals(object? obj)
    {
        if (obj is not Node other)
            return false;

        if (Name != other.Name)
            return false;

        if (attributes.Count != other.attributes.Count)
            return false;

        foreach (var pair in attributes)
        {
            if (!other.attributes.TryGetValue(pair.Key, out var value))
                return false;

            if (pair.Value != value)
                return false;
        }

        return true;
    }
    public override int GetHashCode()
    {
        int hash = HashCode.Combine(Name) + 17;

        foreach (var pair in attributes)
        {
            int pairHash = HashCode.Combine(pair.Key, pair.Value);
            hash += pairHash;
        }

        return hash;
    }
    public override string? ToString() => $"[Name: {Name}, Attrs: {attributes}]";
}

public class Edge : IEnumerable<KeyValuePair<string, string>>
{
    public string From { get; }
    public string To { get; }

    private readonly Dictionary<string, string> attributes = new();

    public Edge(string from, string to)
    {
        From = from;
        To = to;
    }

    public void Add(string key, string value)
    {
        attributes[key] = value;
    }

    public IEnumerator<KeyValuePair<string, string>> GetEnumerator()
    {
        return attributes.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }

    public override bool Equals(object? obj)
    {
        if (obj is not Edge other)
            return false;

        if (From != other.From)
            return false;

        if (To != other.To)
            return false;

        if (attributes.Count != other.attributes.Count)
            return false;

        foreach (var pair in attributes)
        {
            if (!other.attributes.TryGetValue(pair.Key, out var value))
                return false;

            if (pair.Value != value)
                return false;
        }

        return true;
    }
    public override int GetHashCode()
    {
        int hash = HashCode.Combine(From, To) + 17;

        foreach (var pair in attributes)
        {
            int pairHash = HashCode.Combine(pair.Key, pair.Value);
            hash += pairHash;
        }

        return hash;
    }
    public override string? ToString() => $"[From: {From}, To: {To}, Attrs: {attributes}]";
}

public class Attr
{
    public string Key { get; }
    public string Value { get; }

    public Attr(string key, string value)
    {
        Key = key;
        Value = value;
    }

    public override bool Equals(object? obj) => obj is Attr attr && Key == attr.Key && Value == attr.Value;
    public override int GetHashCode() => HashCode.Combine(Key, Value);
    public override string? ToString() => $"[Key: {Key}, Value: {Value}]";
}