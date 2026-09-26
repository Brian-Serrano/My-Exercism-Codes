public static class Knapsack
{
    public static int MaximumValue(int maximumWeight, (int weight, int value)[] items)
    {
        int[,] dp = new int[items.Length + 1, maximumWeight + 1];

        for (int i = 1; i <= items.Length; i++)
        {
            var item = items[i - 1];
            for (int j = 1; j <= maximumWeight; j++)
            {
                if (item.weight <= j)
                {
                    dp[i, j] = Math.Max(dp[i - 1, j], dp[i - 1, j - item.weight] + item.value);
                }
                else
                {
                    dp[i, j] = dp[i - 1, j];
                }
            }
        }
        return dp[items.Length, maximumWeight];
    }
}
