public static class AllYourBase
{
    public static int[] Rebase(int inputBase, int[] inputDigits, int outputBase)
    {
        if (inputBase < 2)
        {
            throw new ArgumentException("Input base must be >= 2.");
        }
        if (outputBase < 2)
        {
            throw new ArgumentException("Output base must be >= 2.");
        }

        List<int> result = new List<int>();
        int dec = 0;
        int n = inputDigits.Length;

        for (int i = 0; i < n; i++)
        {
            if (inputDigits[i] < 0 || inputDigits[i] >= inputBase)
            {
                throw new ArgumentException("All digits must satisfy 0 <= d < input base.");
            }

            dec += (int)Math.Pow(inputBase, n - i - 1) * inputDigits[i];
        }

        while (dec > 0)
        {
            result.Insert(0, dec % outputBase);
            dec /= outputBase;
        }

        return result.Count == 0 ? [0] : result.ToArray();
    }
}