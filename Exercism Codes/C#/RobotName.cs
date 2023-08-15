using System;
using System.Collections.Generic;
using System.Linq;

public class Robot
{
    private static readonly HashSet<string> registered = new();
    private readonly Random random = new();

    public Robot() => Reset();

    public string Name { get; private set; }

    public void Reset()
    {
        string name;
        do
        {
            name = GenerateName();
        } while (registered.Contains(name));
        registered.Add(name);
        Name = name;
    }

    private string GenerateName() => RandomName('A', 'Z', 2) + RandomName('0', '9', 3);

    private string RandomName(char start, char end, int length) => string
        .Join("", Enumerable.Range(0, length)
        .Select(x => (char)random.Next(start, end + 1)));
}