public record Tree
{
    public char Value { get; set; }
    public Tree? Left { get; set; }
    public Tree? Right { get; set; }

    public Tree(char value, Tree? left = null, Tree? right = null)
    {
        Value = value;
        Left = left;
        Right = right;
    }
}

public static class Satellite
{
    public static Tree? TreeFromTraversals(char[] preOrder, char[] inOrder)
    {
        HashSet<char> inOrderSet = new HashSet<char>(inOrder);
        HashSet<char> preOrderSet = new HashSet<char>(preOrder);

        if (inOrderSet.Count != inOrder.Length || preOrderSet.Count != preOrder.Length)
        {
            throw new ArgumentException("traversals must contain unique items");
        }
        if (preOrder.Length != inOrder.Length)
        {
            throw new ArgumentException("traversals must have the same length");
        }
        if (!preOrderSet.SetEquals(inOrderSet))
        {
            throw new ArgumentException("traversals must have the same elements");
        }

        int preOrderIndex = 0;

        Tree? BuildTree(char[] preOrder, char[] inOrder, int start, int end)
        {
            if (start > end)
            {
                return null;
            }

            Tree n = new Tree(preOrder[preOrderIndex++], null, null);

            if (start == end)
            {
                return n;
            }

            int inOrderIndex = Search(inOrder, start, end, n.Value);
            n.Left = BuildTree(preOrder, inOrder, start, inOrderIndex - 1);
            n.Right = BuildTree(preOrder, inOrder, inOrderIndex + 1, end);
            return n;
        }

        return BuildTree(preOrder, inOrder, 0, inOrder.Length - 1);
    }

    private static int Search(char[] inOrder, int start, int end, char n)
    {
        for (int i = start; i <= end; i++)
        {
            if (inOrder[i] == n)
            {
                return i;
            }
        }
        return -1;
    }
}
