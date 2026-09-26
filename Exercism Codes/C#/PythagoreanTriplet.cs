public static class PythagoreanTriplet
{
    public static IEnumerable<(int a, int b, int c)> TripletsWithSum(int sum)
    {
        var result = new List<(int a, int b, int c)>();

        for (int a = 1; a < sum / 3 + 1; a++)
        {
            for (int b = a; b < (sum - a) / 2 + 1; b++)
            {
                int c = sum - a - b;

                if (a * a + b * b == c * c)
                {
                    result.Add((a, b, c));
                }
            }
        }

        return result;
    }
}