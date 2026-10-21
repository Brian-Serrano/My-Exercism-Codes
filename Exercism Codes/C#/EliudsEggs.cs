public static class EliudsEggs
{
    public static int EggCount(int encodedCount)
    {
        return Convert.ToString(encodedCount, 2).Count(c => c == '1');
    }
}
