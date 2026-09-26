public static class Say
{
    private static readonly List<string> ones = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
    private static readonly List<string> tens = ["ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
    private static readonly List<string> teens = ["eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];

    private static string join(List<string> list)
    {
        string result = list[0];

        for (int i = 1; i < list.Count; i++)
        {
            if (tens.Contains(list[i - 1]) && ones.Contains(list[i]))
            {
                result += "-" + list[i];
            }
            else
            {
                result += " " + list[i];
            }
        }

        return result;
    }

    public static string InEnglish(long number)
    {
        if (number == 0)
        {
            return "zero";
        }
        if (number < 0 || number > 999999999999)
        {
            throw new ArgumentOutOfRangeException();
        }

        List<string> result = [];
        string str = number.ToString();

        for (int x = str.Length - 1; x >= 0; x--)
        {
            int idx = str.Length - x - 1;
            if (idx / 3.0 == 1)
            {
                result.Insert(0, "thousand");
            }
            if (idx / 3.0 == 2)
            {
                if (int.Parse(str.Substring(x + 1, 3)) == 0)
                {
                    result.RemoveAt(0);
                }
                result.Insert(0, "million");
            }
            if (idx / 3.0 == 3)
            {
                if (int.Parse(str.Substring(x + 1, 3)) == 0)
                {
                    result.RemoveAt(0);
                }
                result.Insert(0, "billion");
            }

            if (int.Parse(str[x].ToString()) != 0)
            {
                if (idx % 3 == 0)
                {
                    result.Insert(0, ones[int.Parse(str[x].ToString()) - 1]);
                }
                else if (idx % 3 == 1)
                {
                    if (str[x] == '1' && str[x + 1] != '0')
                    {
                        result.RemoveAt(0);
                        result.Insert(0, teens[int.Parse(str[x + 1].ToString()) - 1]);
                    }
                    else
                    {
                        result.Insert(0, tens[int.Parse(str[x].ToString()) - 1]);
                    }
                }
                else if (idx % 3 == 2)
                {
                    result.Insert(0, "hundred");
                    result.Insert(0, ones[int.Parse(str[x].ToString()) - 1]);
                }
            }
        }

        return join(result);
    }
}