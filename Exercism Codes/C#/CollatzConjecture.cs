public static class CollatzConjecture
{
    public static int Steps(int number)
    {
        if (number <= 0)
        {
            throw new ArgumentOutOfRangeException();
        }
        int count = 0;

        for (count = 0; number != 1; count++)
        {
            number = (number % 2 == 0) ? number / 2 : 3 * number + 1;
        }
        return count;
    }
}