public static class ArmstrongNumbers
{
    public static bool IsArmstrongNumber(int number) => number.ToString()
        .Select(x => Math.Pow(int.Parse(x.ToString()), number.ToString().Length)).Sum() == number;
}