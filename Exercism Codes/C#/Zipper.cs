public class BinTree
{
    public BinTree(int value, BinTree? left, BinTree? right)
    {
        Value = value;
        Left = left;
        Right = right;
    }

    public int Value { get; set; }
    public BinTree? Left { get; set; }
    public BinTree? Right { get; set; }

    public override bool Equals(object? obj) => obj is BinTree tree && Value == tree.Value && EqualityComparer<BinTree?>.Default.Equals(Left, tree.Left) && EqualityComparer<BinTree?>.Default.Equals(Right, tree.Right);
    public override int GetHashCode() => HashCode.Combine(Value, Left, Right);
}

public class Zipper
{
    private Zipper? left, right, up;
    private int value;

    public int Value()
    {
        return this.value;
    }

    public Zipper SetValue(int newValue)
    {
        this.value = newValue;
        return this;
    }

    public Zipper SetLeft(BinTree? binTree)
    {
        if (binTree != null)
        {
            this.left = new Zipper(binTree, this);
        }
        else
        {
            this.left = null;
        }
        return this;
    }

    public Zipper SetRight(BinTree? binTree) 
    {
        if (binTree != null)
        {
            this.right = new Zipper(binTree, this);
        }
        else
        {
            this.right = null;
        }
        return this;
    }

    public Zipper? Left()
    {
        return this.left;
    }

    public Zipper? Right()
    {
        return this.right;
    }

    public Zipper? Up()
    {
        return this.up;
    }

    public BinTree ToTree()
    {
        Zipper root = this;

        while (root.up != null)
        {
            root = root.up;
        }

        return GenerateTree(root);
    }

    private BinTree GenerateTree(Zipper child)
    {
        BinTree tree = new BinTree(child.value, null, null);

        if (child.left != null)
        {
            tree.Left = GenerateTree(child.left);
        }
        if (child.right != null)
        {
            tree.Right = GenerateTree(child.right);
        }
        return tree;
    }

    public static Zipper FromTree(BinTree tree)
    {
        return new Zipper(tree, null);
    }

    public override bool Equals(object? obj) => obj is Zipper zipper && EqualityComparer<Zipper?>.Default.Equals(left, zipper.left) && EqualityComparer<Zipper?>.Default.Equals(right, zipper.right) && value == zipper.value;
    public override int GetHashCode() => HashCode.Combine(left, right, value);

    private Zipper(BinTree tree, Zipper? up)
    {
        this.value = tree.Value;
        this.up = up;
        this.left = null;
        this.right = null;

        if (tree.Left != null)
        {
            this.left = new Zipper(tree.Left, this);
        }
        if (tree.Right != null)
        {
            this.right = new Zipper(tree.Right, this);
        }
    }
}