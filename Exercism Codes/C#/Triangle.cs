using System;

public static class Triangle
{
    public static bool IsScalene(double side1, double side2, double side3)
    {
        return (side1 != side2 && side1 != side3 && side2 != side3) && CheckInequalityAndZero(side1, side2, side3);
    }

    public static bool IsIsosceles(double side1, double side2, double side3)
    {
        return (side1 == side2 || side1 == side3 || side2 == side3) && CheckInequalityAndZero(side1, side2, side3);
    }

    public static bool IsEquilateral(double side1, double side2, double side3)
    {
        return (side1 == side2 && side1 == side3 && side2 == side3) && CheckInequalityAndZero(side1, side2, side3);
    }

    private static bool CheckInequalityAndZero(double a, double b, double c)
    {
        return a != 0 && b != 0 && c != 0 && a + b >= c && a + c >= b && b + c >= a;
    }
}