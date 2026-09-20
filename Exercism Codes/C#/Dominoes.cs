public static class Dominoes
{
    public static bool CanChain(IEnumerable<(int, int)> dominoes)
    {
        List<(int, int)> input = dominoes.ToList();
        List<(int, int)> output = new List<(int, int)>();
        int x = 0, y = 0;

        if (input.Count > 0)
        {
            (int, int) item = input[0];
            input.RemoveAt(0);
            output.Add(item);
        }

        while (true)
        {
            if (input.Count == 0)
            {
                if (output.Count == 0)
                {
                    break;
                }
                if (output[0].Item1 != output[^1].Item2)
                {
                    return false;
                }
                break;
            }
            if (output[0].Item1 == input[0].Item1)
            {
                output.Insert(0, (input[0].Item2, input[0].Item1));
                input.RemoveAt(0);
                x = 0;
                y = 0;
                continue;
            }
            if (output[0].Item1 == input[0].Item2)
            {
                output.Insert(0, input[0]);
                input.RemoveAt(0);
                x = 0;
                y = 0;
                continue;
            }
            if (output[^1].Item2 == input[0].Item1)
            {
                output.Add(input[0]);
                input.RemoveAt(0);
                x = 0;
                y = 0;
                continue;
            }
            if (output[^1].Item2 == input[0].Item2)
            {
                output.Add((input[0].Item2, input[0].Item1));
                input.RemoveAt(0);
                x = 0;
                y = 0;
                continue;
            }
            (int, int) item = input[0];
            input.RemoveAt(0);
            input.Add(item);
            if (++x >= input.Count)
            {
                if (output[0].Item1 != output[^1].Item2)
                {
                    return false;
                }
                else
                {
                    (int, int) item2 = output[0];
                    output.RemoveAt(0);
                    output.Add(item2);
                    x = 0;
                    if (++y >= output.Count)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}