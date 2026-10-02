using System.Collections;

public class SimpleLinkedList<T> : IEnumerable<T>
{
    private List<T> elements;

    public SimpleLinkedList()
    {
        elements = new List<T>();
    }

    public SimpleLinkedList(T value)
    {
        elements = new List<T> { value };
    }

    public SimpleLinkedList(T[] values)
    {
        elements = values.ToList();
    }

    public int Count => elements.Count;
    
    public void Push(T value)
    {
        elements.Add(value);
    }

    public T Pop()
    {
        T element = elements[elements.Count - 1];
        elements.RemoveAt(elements.Count - 1);
        return element;
    }

    public IEnumerator<T> GetEnumerator() => elements.OrderDescending().GetEnumerator();
    IEnumerator IEnumerable.GetEnumerator() => GetEnumerator();
}