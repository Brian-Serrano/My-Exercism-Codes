public static class NthPrime
{
    public static int Prime(int nth)
    {
        if (nth <= 0)
        {
            throw new ArgumentOutOfRangeException(nameof(nth), "There is no zeroth prime.");
        }
        if (nth == 1)
        {
            return 2;
        }

        int i = 1;
        int count = 1;

        while (count != nth)
        {
            i += 2;
            if (isPrime(i))
            {
                count++;
            }
        }

        return i;
    }

    private static bool isPrime(int number)
    {
        for (int i = 3; i <= Math.Sqrt(number); i += 2)
        {
            if (number % i == 0)
            {
                return false;
            }
        }

        return true;
    }
}