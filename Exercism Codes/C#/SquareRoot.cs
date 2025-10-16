public static class SquareRoot
{
    public static int Root(int number)
    {
        double x = number;
        do
        {
            x = 0.5 * (x + (number / x));
        }
        while (Math.Abs(0.5 * (x + (number / x)) - x) >= 0.00001);
        return (int)x;
    }
}
