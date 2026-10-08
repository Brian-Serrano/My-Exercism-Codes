public static class PalindromeProducts
{
    public static (int, IEnumerable<(int,int)>) Largest(int minFactor, int maxFactor)
    {
        if (minFactor > maxFactor)
            throw new ArgumentException();

        int? maximum = null;
        var factors = new List<(int, int)>();
        for (int i = minFactor; i <= maxFactor; i++)
        {
            for (int j = i; j <= maxFactor; j++)
            {
                int product = i * j;

                if (maximum != null && product < maximum)
                    continue;

                if (IsPalindrome(product))
                {
                    if (maximum == null || product > maximum)
                    {
                        maximum = product;
                        factors.Clear();
                    }
                    factors.Add((i, j));
                }
            }
        }
        return maximum != null ? (maximum.Value, factors) : throw new ArgumentException();
    }

    public static (int, IEnumerable<(int,int)>) Smallest(int minFactor, int maxFactor)
    {
        if (minFactor > maxFactor)
            throw new ArgumentException();

        int? minimum = null;
        var factors = new List<(int, int)>();

        for (int i = minFactor; i <= maxFactor; i++)
        {
            for (int j = i; j <= maxFactor; j++)
            {
                int product = i * j;

                if (minimum != null && product > minimum)
                    continue;

                if (IsPalindrome(product))
                {
                    if (minimum == null || product < minimum)
                    {
                        minimum = product;
                        factors.Clear();
                    }
                    factors.Add((i, j));
                }
            }
        }
        return minimum != null ? (minimum.Value, factors) : throw new ArgumentException();
    }

    private static bool IsPalindrome(int number)
    {
        string str = number.ToString();
        return str.SequenceEqual(str.Reverse());
    }
}
