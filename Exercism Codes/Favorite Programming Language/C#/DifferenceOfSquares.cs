using System;
using System.Linq;

public static class DifferenceOfSquares
{
    public static int CalculateSquareOfSum(int max)
    {
        int squareOfSum = Enumerable.Range(0, max + 1).Sum();
        return squareOfSum * squareOfSum;
    }

    public static int CalculateSumOfSquares(int max)
    {
        return Enumerable.Range(0, max + 1).Select(n => n * n).Sum();
    }

    public static int CalculateDifferenceOfSquares(int max)
    {
        return CalculateSquareOfSum(max) - CalculateSumOfSquares(max);
    }
}