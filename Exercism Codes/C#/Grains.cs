public static class Grains
{
    public static ulong Square(int n) => n < 1 || n > 64 ? throw new ArgumentOutOfRangeException() : 1UL << (n - 1);

    public static ulong Total() => (Square(64) << 1) - 1;
}