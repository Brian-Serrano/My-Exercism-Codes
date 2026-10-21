public class Reactor
{
    public InputCell CreateInputCell(int value)
    {
        return new InputCell(value);
    }

    public ComputeCell CreateComputeCell(IEnumerable<Cell> producers, Func<int[], int> compute)
    {
        return new ComputeCell(producers, compute);
    }
}

public abstract class Cell
{
    public List<Cell> cells;
    public List<Observer> observers;

    public abstract int Value { get; set; }
}

public class InputCell : Cell
{
    private int _value;

    public override int Value {
        get => _value;
        set
        {
            List<int> oldValues = new List<int>();

            foreach (var observer in observers)
            {
                oldValues.Add(observer.cell.Value);
            }

            _value = value;

            for (int i = 0; i < observers.Count; i++)
            {
                var observer = observers[i];
                int newValue = observer.cell.Value;
                if (newValue != oldValues[i])
                {
                    observer.callback?.Invoke(observer.cell, newValue);
                }
            }
        }
    }

    public InputCell(int value)
    {
        _value = value;
        observers = new List<Observer>();
    }
}

public class ComputeCell : Cell
{
    private Func<int[], int> compute;

    public override int Value {
        get => compute(cells.Select(c => c.Value).ToArray());
        set => throw new NotImplementedException();
    }

    public ComputeCell(IEnumerable<Cell> producers, Func<int[], int> compute)
    {
        this.cells = producers.ToList();
        this.compute = compute;
    }

    public event EventHandler<int> Changed
    {
        add => Add(value, cells, new HashSet<Cell>()); remove => Remove(value, cells);
    }

    private void Add(EventHandler<int> callback, List<Cell> cells, HashSet<Cell> visited)
    {
        foreach (var cell in cells)
        {
            if (!visited.Add(cell))
                continue;

            if (cell is InputCell)
                cell.observers.Add(new Observer(this, callback));
            else
                Add(callback, cell.cells, visited);
        }
    }

    private void Remove(EventHandler<int> callback, List<Cell> cells)
    {
        foreach (var cell in cells)
        {
            if (cell is InputCell)
            {
                List<EventHandler<int>> callbacks = cell.observers.Select(c => c.callback).ToList();
                if (callbacks.Contains(callback))
                {
                    cell.observers.RemoveAt(callbacks.IndexOf(callback));
                }
            }
            else
                Remove(callback, cell.cells);
        }
    }
}

public class Observer
{
    public ComputeCell cell;
    public EventHandler<int> callback;

    public Observer(ComputeCell cell, EventHandler<int> callback)
    {
        this.cell = cell;
        this.callback = callback;
    }
}