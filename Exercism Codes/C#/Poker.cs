public static class Poker
{
    private class HandInfo
    {
        public string Hand { get; }
        public int Category { get; }
        public List<int> Ranks { get; }

        public HandInfo(string hand, int category, List<int> ranks)
        {
            Hand = hand;
            Category = category;
            Ranks = ranks;
        }
    }

    private static Dictionary<string, int> rankValues = new Dictionary<string, int>
    {
        ["2"] = 2,
        ["3"] = 3,
        ["4"] = 4,
        ["5"] = 5,
        ["6"] = 6,
        ["7"] = 7,
        ["8"] = 8,
        ["9"] = 9,
        ["10"] = 10,
        ["J"] = 11,
        ["Q"] = 12,
        ["K"] = 13,
        ["A"] = 14
    };

    public static IEnumerable<string> BestHands(IEnumerable<string> hands)
    {
        List<HandInfo> handInfo = new List<HandInfo>();

        foreach (string s in hands)
        {
            var straightFlush = IsStraightFlush(s);
            var square = IsSquare(s);
            var fullHouse = IsFullHouse(s);
            var flush = IsFlush(s);
            var straight = IsStraight(s);
            var threeOfKind = IsThreeOfKind(s);
            var twoPair = IsTwoPair(s);
            var onePair = IsOnePair(s);

            if (straightFlush.Item2)
                handInfo.Add(new HandInfo(s, 9, straightFlush.Item1));
            else if (square.Item2)
                handInfo.Add(new HandInfo(s, 8, square.Item1));
            else if (fullHouse.Item2)
                handInfo.Add(new HandInfo(s, 7, fullHouse.Item1));
            else if (flush.Item2)
                handInfo.Add(new HandInfo(s, 6, flush.Item1));
            else if (straight.Item2)
                handInfo.Add(new HandInfo(s, 5, straight.Item1));
            else if (threeOfKind.Item2)
                handInfo.Add(new HandInfo(s, 4, threeOfKind.Item1));
            else if (twoPair.Item2)
                handInfo.Add(new HandInfo(s, 3, twoPair.Item1));
            else if (onePair.Item2)
                handInfo.Add(new HandInfo(s, 2, onePair.Item1));
            else
                handInfo.Add(new HandInfo(s, 1, GetRank(s)));
        }

        int max = handInfo[0].Category;
        foreach (HandInfo hand in handInfo)
        {
            if (hand.Category > max)
            {
                max = hand.Category;
            }
        }
        List<HandInfo> maxHands = handInfo.Where(h => h.Category == max).ToList();
        if (maxHands.Count > 1)
        {
            List<string> hand = new List<string>();
            List<List<int>> ranks = new List<List<int>>();
            foreach (HandInfo maxHand in maxHands)
            {
                hand.Add(maxHand.Hand);
                ranks.Add(maxHand.Ranks);
            }
            return Highest(hand, ranks);
        }
        return maxHands.Select(h => h.Hand);
    }

    private static(List<int>, bool) IsStraightFlush(string hand)
    {
        List<int> rank = GetRank(hand);
        List<char> suit = GetSuit(hand);
        CheckAce(rank);
        return ([rank[^1]], suit.Distinct().Count() == 1 && Enumerable.Range(1, 4).All(n => rank[n] + 1 == rank[n - 1]));
    }

    private static (List<int>, bool) IsSquare(string hand)
    {
        List<int> rank = GetRank(hand);
        int first = rank[0];
        int last = rank[^1];
        int num1 = rank.Count(x => x == first);
        int num2 = rank.Count(x => x == last);
        return ([num1 == 4 ? first : last, num1 == 4 ? last : first], num2 == 4 || num1 == 4);
    }

    private static (List<int>, bool) IsFullHouse(string hand)
    {
        List<int> rank = GetRank(hand);
        int first = rank[0];
        int last = rank[^1];
        int num1 = rank.Count(x => x == first);
        int num2 = rank.Count(x => x == last);
        return (
            [num1 == 3 ? first : last, num1 == 3 ? last : first],
            new List<int> { 2, 3 }.SequenceEqual(new List<int> { num2, num1 }.Order())
            );
    }

    private static (List<int>, bool) IsFlush(string hand)
    {
        List<char> suit = GetSuit(hand);
        return (GetRank(hand), suit.Distinct().Count() == 1);
    }

    private static (List<int>, bool) IsStraight(string hand)
    {
        List<int> rank = GetRank(hand);
        CheckAce(rank);
        return ([rank[^1]], Enumerable.Range(1, 4).All(n => rank[n] + 1 == rank[n - 1]));
    }

    private static (List<int>, bool) IsThreeOfKind(string hand)
    {
        List<int> rank = GetRank(hand);
        int middle = rank[rank.Count / 2];
        int num1 = rank.Count(x => x == middle);
        List<int> result = new List<int>();
        result.Add(middle);
        result.AddRange(rank.Where(c => rank.Count(x => x == c) == 1));
        return (result, num1 == 3);
    }

    private static (List<int>, bool) IsTwoPair(string hand)
    {
        List<int> rank = GetRank(hand);
        int second = rank[1];
        int fourth = rank[3];
        int num1 = rank.Count(x => x == second);
        int num2 = rank.Count(x => x == fourth);
        List<int> result = new List<int>();
        result.Add(second);
        result.Add(fourth);
        result.Add(rank.Where(c => rank.Count(x => x == c) == 1).FirstOrDefault(rank[0]));
        return (result, num1 == 2 && num2 == 2);
    }

    private static (List<int>, bool) IsOnePair(string hand)
    {
        List<int> rank = GetRank(hand);
        List<int> pairs = rank.Distinct().Where(c => rank.Count(x => x == c) == 2).ToList();
        List<int> result = new List<int>();
        if (!(pairs.Count == 0))
            result.Add(pairs[0]);
        result.AddRange(rank.Where(c => rank.Count(x => x == c) == 1));
        return (result, pairs.Count == 1);
    }

    private static List<string> Highest(List<string> hands, List<List<int>> ranks)
    {
        List<string> result = new List<string>();
        List<int> highest = ranks[0];
        foreach (List<int> rank in ranks)
        {
            if (Larger(rank, highest))
            {
                highest = rank;
            }
        }
        for (int i = 0; i < ranks.Count; i++)
        {
            if (highest.SequenceEqual(ranks[i]))
            {
                result.Add(hands[i]);
            }
        }
        return result;
    }

    private static void CheckAce(List<int> rank)
    {
        if (Enumerable.Range(2, 3).All(n => rank[n] + 1 == rank[n - 1]) && rank[1] == 5 && rank[0] == 14)
        {
            rank[0] = 1;
            rank.Sort((a, b) => b.CompareTo(a));
        }
    }

    private static bool Larger(List<int> lst1, List<int> lst2)
    {
        for (int i = 0; i < lst1.Count; i++)
        {
            if (lst1[i] > lst2[i])
                return true;
            if (lst1[i] < lst2[i])
                return false;
        }
        return false;
    }

    private static List<int> GetRank(string hand)
    {
        return hand.Split(" ").Select(c => rankValues[c.Substring(0, c.Length - 1)]).OrderDescending().ToList();
    }

    private static List<char> GetSuit(string hand)
    {
        return hand.Split(" ").Select(c => c[^1]).ToList();
    }
}