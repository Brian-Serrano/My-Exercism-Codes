public enum Classification
{
    Perfect,
    Abundant,
    Deficient
}

public static class PerfectNumbers
{
    public static Classification Classify(int number)
    {
        if (number <= 0)
        {
            throw new ArgumentOutOfRangeException("Classification is only possible for natural numbers.");
        }
        int aliquot = Enumerable.Range(1, number - 1).Where(i => number % i == 0).Sum();
        return aliquot > number ? Classification.Abundant :
               aliquot < number ? Classification.Deficient :
               Classification.Perfect;
    }
}
