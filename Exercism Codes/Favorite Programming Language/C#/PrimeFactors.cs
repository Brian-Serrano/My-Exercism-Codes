public static class PrimeFactors
{
    public static long[] Factors(long number)
    {
        List<long> factors = [];
        long factor = 2;
        while (number > 1)
        {
            if (number % factor == 0)
            {
                factors.Add(factor);
                number /= factor;
            }
            else
            {
                factor++;
            }
        }

        return [..factors];
    }
}