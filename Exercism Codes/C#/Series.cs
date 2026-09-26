public static class Series
{
    public static string[] Slices(string numbers, int sliceLength)
    {
        if (sliceLength > numbers.Length || sliceLength <= 0 || numbers.Length == 0)
        {
            throw new ArgumentException();
        }

        List<string> result = [];
        for (int i = 0; i <= numbers.Length - sliceLength; i++)
        {
            result.Add(numbers.Substring(i, sliceLength));
        }
        return [..result];
    }
}