using System.Text.RegularExpressions;

public static class Forth
{
    public static string Evaluate(string[] instructions)
    {
        List<string> tokens = new List<string>();
        List<List<string>> func = new List<List<string>>();
        List<string> funcName = new List<string>();

        foreach (string instruction in instructions)
        {
            List<string> f = Regex.Matches(instruction.ToLower(), @"[^ :;]+").Select(m => m.Value).ToList();
            if (Regex.IsMatch(instruction, @"(^:.*;$)"))
            {
                if (IsNumber(f[0]))
                    throw new InvalidOperationException();
                funcName.Insert(0, f[0]);
                func.Insert(0, f.GetRange(1, f.Count - 1));
            }
            else
            {
                tokens.AddRange(f);
            }
        }
        return string.Join(" ", Call(new Stack<int>(), tokens, func, funcName, 0).Reverse());
    }

    private static Stack<int> Call(Stack<int> stack, List<string> tokens, List<List<string>> func, List<string> funcName, int where)
    {
        foreach (string token in tokens)
        {
            int index = funcName.IndexOf(token);
            if (index != -1)
            {
                Call(stack, func.GetRange(where, func.Count - where)[index], func, funcName, index + 1);
                continue;
            }
            if (IsNumber(token))
            {
                stack.Push(int.Parse(token));
                continue;
            }
            if (token == "+")
            {
                if (stack.Count < 2)
                    throw new InvalidOperationException();
                stack.Push(stack.Pop() + stack.Pop());
                continue;
            }
            if (token == "-")
            {
                if (stack.Count < 2)
                    throw new InvalidOperationException();
                int first = stack.Pop();
                int second = stack.Pop();
                stack.Push(second - first);
                continue;
            }
            if (token == "*")
            {
                if (stack.Count < 2)
                    throw new InvalidOperationException();
                stack.Push(stack.Pop() * stack.Pop());
                continue;
            }
            if (token == "/")
            {
                if (stack.Count < 2)
                    throw new InvalidOperationException();
                int first = stack.Pop();
                if (first == 0)
                    throw new DivideByZeroException();
                int second = stack.Pop();
                stack.Push(second / first);
                continue;
            }
            if (token == "dup")
            {
                if (stack.Count < 1)
                    throw new InvalidOperationException();
                stack.Push(stack.Peek());
                continue;
            }
            if (token == "drop")
            {
                if (stack.Count < 1)
                    throw new InvalidOperationException();
                stack.Pop();
                continue;
            }
            if (token == "swap")
            {
                if (stack.Count < 2)
                    throw new InvalidOperationException();
                int first = stack.Pop();
                int second = stack.Pop();
                stack.Push(first);
                stack.Push(second);
                continue;
            }
            if (token == "over")
            {
                if (stack.Count < 2)
                    throw new InvalidOperationException();
                stack.Push(stack.Reverse().ElementAt(stack.Count - 2));
                continue;
            }
            throw new InvalidOperationException();
        }
        return stack;
    }

    private static bool IsNumber(string token)
    {
        try
        {
            int.Parse(token);
            return true;
        }
        catch (Exception)
        {
            return false;
        }
    }
}