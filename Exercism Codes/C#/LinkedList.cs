public class Deque<T>
{
    private List<T> elements;

    public Deque()
    {
        elements = new List<T>();
    }

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

    public void Unshift(T value)
    {
        elements.Insert(0, value);
    }

    public T Shift()
    {
        T element = elements[0];
        elements.RemoveAt(0);
        return element;
    }
}