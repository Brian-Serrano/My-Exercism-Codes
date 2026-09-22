using System;
using System.Collections.Generic;
using System.Linq;
public class CircularBuffer<T>
{
    private int capacity;
    private List<T> elements;
    public CircularBuffer(int capacity)
    {
        this.capacity = capacity;
        elements = new List<T>();
    }
    public T Read()
    {
        if (elements.Count != 0) {
            T val = elements.First();
            elements.RemoveAt(0);
            return val;
        }
        throw new InvalidOperationException();
    }
    public void Write(T value)
    {
        if(elements.Count == capacity)
        {
            throw new InvalidOperationException();
        }
        elements.Add(value);
    }
    public void Overwrite(T value)
    {
        if(elements.Count == capacity)
        {
            elements.RemoveAt(0);
        }
        Write(value);
    }
    public void Clear()
    {
        if (elements.Count != 0)
        {
            elements.Clear();
        }
    }
}