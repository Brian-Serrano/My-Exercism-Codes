public static class RealNumberExtension
{
    public static double Expreal(this int realNumber, RationalNumber r)
    {
        return Math.Pow(Math.Pow(realNumber, r.numerator), 1.0 / r.denominator);
    }
}

public struct RationalNumber
{
    public int numerator, denominator;

    public RationalNumber(int numerator, int denominator)
    {
        int gcf = GCF(numerator, denominator);
        this.numerator = numerator / gcf;
        this.denominator = denominator / gcf;
    }

    public static RationalNumber operator +(RationalNumber r1, RationalNumber r2)
    {
        int lcm = LCM(r1.denominator, r2.denominator);
        return Reduce(r1.numerator * (lcm / r1.denominator) + r2.numerator * (lcm / r2.denominator), lcm);
    }

    public static RationalNumber operator -(RationalNumber r1, RationalNumber r2)
    {
        int lcm = LCM(r1.denominator, r2.denominator);
        return Reduce(r1.numerator * (lcm / r1.denominator) - r2.numerator * (lcm / r2.denominator), lcm);
    }

    public static RationalNumber operator *(RationalNumber r1, RationalNumber r2)
    {
        return Reduce(r1.numerator * r2.numerator, r1.denominator * r2.denominator);
    }

    public static RationalNumber operator /(RationalNumber r1, RationalNumber r2)
    {
        return Reduce(r1.numerator * r2.denominator, r2.numerator * r1.denominator);
    }

    public RationalNumber Abs()
    {
        return Reduce(Math.Abs(numerator), Math.Abs(denominator));
    }

    public RationalNumber Reduce()
    {
        return Reduce(numerator, denominator);
    }

    public RationalNumber Exprational(int power)
    {
        int a = (int)Math.Pow(numerator, Math.Abs(power));
        int b = (int)Math.Pow(denominator, Math.Abs(power));
        return power >= 0 ? Reduce(a, b) : Reduce(b, a);
    }

    public double Expreal(int baseNumber)
    {
        return Math.Pow(Math.Pow(baseNumber, numerator), 1.0 / denominator);
    }

    private static int GCF(int a, int b)
    {
        while (b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int LCM(int a, int b)
    {
        return (a * b) / GCF(a, b);
    }

    private static RationalNumber Reduce(int numerator, int denominator)
    {
        int gcf = GCF(numerator, denominator);
        return new RationalNumber(numerator / gcf, denominator / gcf);
    }
}