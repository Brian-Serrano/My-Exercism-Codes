public static class Etl
{
    public static Dictionary<string, int> Transform(Dictionary<int, string[]> old)
    {
        Dictionary<string, int> result = new Dictionary<string, int>();
        foreach (var item in old)
        {
            foreach (var i in item.Value)
            {
                result.Add(i.ToLower(), item.Key);
            }
        }
        return result;
    }
}