public enum YachtCategory
{
    Ones = 1,
    Twos = 2,
    Threes = 3,
    Fours = 4,
    Fives = 5,
    Sixes = 6,
    FullHouse = 7,
    FourOfAKind = 8,
    LittleStraight = 9,
    BigStraight = 10,
    Choice = 11,
    Yacht = 12,
}

public static class YachtGame
{
    public static int Score(int[] dice, YachtCategory category)
    {
        List<Func<List<int>, int>> functions = new List<Func<List<int>, int>>
        {
            x => x.Sum(d => d == 1 ? d : 0),
            x => x.Sum(d => d == 2 ? d : 0),
            x => x.Sum(d => d == 3 ? d : 0),
            x => x.Sum(d => d == 4 ? d : 0),
            x => x.Sum(d => d == 5 ? d : 0),
            x => x.Sum(d => d == 6 ? d : 0),
            x => new HashSet<int> { 2, 3 }.SetEquals(x.Distinct().Select(d => x.Count(y => y == d))) ? x.Sum() : 0,
            x => x.Distinct().Where(d => x.Count(y => y == d) >= 4).Sum() * 4,
            x => new HashSet<int> { 1, 2, 3, 4, 5 }.SetEquals(x) ? 30 : 0,
            x => new HashSet<int> { 2, 3, 4, 5, 6 }.SetEquals(x) ? 30 : 0,
            x => x.Sum(),
            x => x.Distinct().Count() == 1 ? 50 : 0
        };

        return functions[((int)category) - 1](dice.ToList());
    }
}

