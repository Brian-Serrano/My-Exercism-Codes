public static class Proverb
{
    public static string[] Recite(string[] subjects)
    {
        List<string> sentence = new List<string>();
        for (int i = 0; i < subjects.Length; i++)
        {
            if (i < subjects.Length - 1)
            {
                sentence.Add($"For want of a {subjects[i]} the {subjects[i + 1]} was lost.");
            }
            else
            {
                sentence.Add($"And all for the want of a {subjects[0]}.");
            }
        }
        return sentence.ToArray();
    }
}