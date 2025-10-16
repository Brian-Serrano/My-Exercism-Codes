public static class IsbnVerifier
{
    public static bool IsValid(string number)
    {
        string isbn = number.Replace("-", "");
        int n = isbn.Length;
        if (n != 10)
        {
            return false;
        }

        int total = 0;
        for (int i = 0; i < n; i++)
        {
            int m = n - i;
            if (i == n - 1 && isbn[i] == 'X')
            {
                total += 10 * m;
                continue;
            }
            if (char.IsDigit(isbn[i]))
            {
                total += (isbn[i] - '0') * m;
            }
            else
            {
                return false;
            }
        }
        return total % 11 == 0;
    }
}