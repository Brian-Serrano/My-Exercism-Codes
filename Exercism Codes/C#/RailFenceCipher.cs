using System;
using System.Collections.Generic;
using System.Linq;

public class RailFenceCipher
{
    private int rails;

    public RailFenceCipher(int rails)
    {
        this.rails = rails;
    }

    public string Encode(string input) => string.Join("", CreateFence(input.Length)
        .Select(x => input[x]).ToList());

    public string Decode(string input)
    {
        List<int> fence = CreateFence(input.Length);
        return string.Join("", Enumerable.Range(0, input.Length)
            .Select(x => input[fence.IndexOf(x)]));
    }

    private List<int> CreateFence(int inputLength)
    {
        int cycle = 2 * rails - 2;
        return Enumerable.Range(0, rails)
            .SelectMany(n => Enumerable.Range(0, inputLength)
            .Where(m => m % cycle == n || m % cycle == cycle - n))
            .ToList();
    }
}