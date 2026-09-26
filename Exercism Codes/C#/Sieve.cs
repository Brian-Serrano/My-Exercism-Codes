public static class Sieve
{
    public static int[] Primes(int limit)
    {
        bool[] prime = Enumerable.Repeat(true, limit + 1).ToArray();
        List<int> result = new List<int>();

        for (int p = 2; p * p <= limit; p++)
            if (prime[p])
                for (int i = p * p; i < limit + 1; i += p)
                    prime[i] = false;
        for (int i = 2; i < limit + 1; i++)
            if (prime[i])
                result.Add(i);

        return result.ToArray();
    }
}