public static class Wordy
{
    public static int Answer(string question)
    {
        List<string> splittedQuestion = question[..^1]
            .Split(" ").Skip(2).Where(x => x != "by").ToList();
        bool notValid = splittedQuestion.Any(i => !(int.TryParse(i, out _) || new List<string> { "plus", "minus", "multiplied", "divided" }.Contains(i)));

        if (notValid)
            throw new ArgumentException();

        try
        {
            if (splittedQuestion.Count % 2 == 0)
                throw new ArgumentException();

            int count = int.Parse(splittedQuestion[0]);
            for (int x = 2; x < splittedQuestion.Count; x += 2)
            {
                switch (splittedQuestion[x - 1])
                {
                    case "plus":
                        count += int.Parse(splittedQuestion[x]);
                        break;
                    case "minus":
                        count -= int.Parse(splittedQuestion[x]);
                        break;
                    case "multiplied":
                        count *= int.Parse(splittedQuestion[x]);
                        break;
                    case "divided":
                        count /= int.Parse(splittedQuestion[x]);
                        break;
                    default:
                        throw new ArgumentException();
                }
            }
            return count;
        }
        catch (Exception)
        {
            throw new ArgumentException();
        }
    }
}