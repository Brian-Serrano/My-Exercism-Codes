public static class Luhn
{
    public static bool IsValid(string number)
    {
        string cardNum = number.Replace(" ", "");
        if (!cardNum.All(c => char.IsDigit(c)) || cardNum.Length < 2)
        {
            return false;
        }

        List<int> digits = cardNum.Select(c => int.Parse($"{c}")).ToList();
        for (int i = digits.Count % 2; i < digits.Count; i += 2)
        {
            digits[i] *= 2;
            if (digits[i] > 9)
            {
                digits[i] -= 9;
            }
        }
        return digits.Sum() % 10 == 0;
    }
}