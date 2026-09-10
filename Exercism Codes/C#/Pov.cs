public class Tree : IComparable<Tree>
{
    public int CompareTo(Tree? other)
    {
        if (other == null) return 1;
        return string.Compare(value, other.value, StringComparison.Ordinal);
    }

    public string value;
    public Tree[] children;

    public Tree(string value, params Tree[] children)
    {
        this.value = value;
        this.children = children;
    }

    public override bool Equals(object? obj) => obj is Tree tree && 
        value == tree.value && 
        children.OrderBy(x => x).SequenceEqual(tree.children.OrderBy(x => x));
}

public static class Pov
{
    public static Tree FromPov(Tree tree, string from)
    {
        Dictionary<string, Neighbor> treeData = GetNeighbors(tree, new Dictionary<string, Neighbor>(), null);

        if (!treeData.ContainsKey(from))
        {
            throw new ArgumentException("Tree could not be reoriented");
        }

        return ConstructTree(treeData, from, "");
    }

    public static IEnumerable<string> PathTo(string from, string to, Tree tree)
    {
        Dictionary<string, Neighbor> treeData = GetNeighbors(FromPov(tree, from), new Dictionary<string, Neighbor>(), null);

        if (!treeData.ContainsKey(to) || !treeData.ContainsKey(from))
        {
            throw new ArgumentException("No path found");
        }

        return FindPath([from], treeData, from, to, new HashSet<string>());
    }

    private static Dictionary<string, Neighbor> GetNeighbors(Tree tree, Dictionary<string, Neighbor> trees, Tree? parent)
    {
        Neighbor neighbors = new Neighbor();
        if (parent != null)
        {
            neighbors.parent = parent.value;
        }
        if (tree.children.Length != 0)
        {
            foreach (Tree child in tree.children)
            {
                neighbors.children.Add(child.value);
                GetNeighbors(child, trees, tree);
            }
        }
        trees[tree.value] = neighbors;
        return trees;
    }

    private static Tree ConstructTree(Dictionary<string, Neighbor> treeData, string nodeLabel, string parentLabel)
    {
        List<Tree> children = new List<Tree>();
        Neighbor neighbors = treeData[nodeLabel];
        List<string> neighborLabels = [.. neighbors.children];
        if (neighbors.parent != "")
        {
            neighborLabels.Add(neighbors.parent);
        }
        foreach (string label in neighborLabels)
        {
            if (label != parentLabel)
            {
                children.Add(ConstructTree(treeData, label, nodeLabel));
            }
        }
        return new Tree(nodeLabel, children.ToArray());
    }

    private static List<string> FindPath(List<string> path, Dictionary<string, Neighbor> treeData, string nodeLabel, string toLabel, HashSet<string> visited)
    {
        if (nodeLabel == toLabel)
        {
            return path;
        }
        Neighbor neighbor = treeData[nodeLabel];
        if (neighbor.children.Count == 0 || visited.IsSupersetOf(neighbor.children))
        {
            path.Remove(nodeLabel);
            visited.Add(nodeLabel);
            return FindPath(path, treeData, neighbor.parent, toLabel, visited);
        }
        else
        {
            foreach (string child in neighbor.children)
            {
                if (!visited.Contains(child))
                {
                    path.Add(child);
                    return FindPath(path, treeData, child, toLabel, visited);
                }
            }
        }
        return path;
    }
}

public class Neighbor
{
    public string parent;
    public List<string> children;

    public Neighbor()
    {
        this.parent = "";
        this.children = new List<string>();
    }
}