using System.Collections;

public class BinarySearchTree : IEnumerable<int>
{
    public BinarySearchTree(int value)
    {
        Add(value);
    }

    public BinarySearchTree(IEnumerable<int> values)
    {
        List<int> val = values.ToList();

        for (int i = 0; i < values.Count(); i++)
        {
            Add(val[i]);
        }
    }

    public int Value { get; set; }

    public BinarySearchTree? Left { get; set; }

    public BinarySearchTree? Right { get; set; }

    public void Add(int value)
    {
        if (this.Value > 0)
        {
            Insert(value, this);
        }
        else
        {
            this.Value = value;
        }
    }

    private void Insert(int value, BinarySearchTree? node)
    {
        if (node?.Value.CompareTo(value) >= 0)
        {
            if (node?.Left != null)
            {
                Insert(value, node?.Left);
            }
            else
            {
                node?.Left = new BinarySearchTree(value);
            }
        }
        else
        {
            if (node?.Right != null)
            {
                Insert(value, node?.Right);
            }
            else
            {
                node?.Right = new BinarySearchTree(value);
            }
        }
    }

    public IEnumerator<int> GetEnumerator()
    {
        return InOrderTraversal(this, new List<int>()).GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }

    private List<int> InOrderTraversal(BinarySearchTree? node, List<int> nodeData)
    {
        if (node != null)
        {
            InOrderTraversal(node.Left, nodeData);
            nodeData.Add(node.Value);
            InOrderTraversal(node.Right, nodeData);
        }
        return nodeData;
    }
}