using System;

public static class Camicia
{
    public enum GameStatus
    {
        Finished,
        Loop
    }

    public record GameResult(GameStatus Status, int Tricks, int Cards);

    public static GameResult SimulateGame(string[] playerA, string[] playerB)
    {
        List<int> handA = playerA.Select(GetValue).ToList();
        List<int> handB = playerB.Select(GetValue).ToList();
        string turn = "A";
        List<int> pile = new List<int>();
        HashSet<State> seenStates = new HashSet<State>();
        int totalTricks = 0;
        int cardsPlayed = 0;
        int currentDebt = 0;

        while (true)
        {
            if (pile.Count == 0)
            {
                State state = new State(new List<int>(handA), new List<int>(handB), turn);
                if (seenStates.Contains(state))
                {
                    return new GameResult(GameStatus.Loop, totalTricks, cardsPlayed);
                }
                seenStates.Add(state);
            }

            List<int> activeHands = turn == "A" ? handA : handB;
            List<int> otherHands = turn == "A" ? handB : handA;

            if (activeHands.Count == 0)
            {
                int extraTrick = pile.Count == 0 ? 0 : 1;
                return new GameResult(GameStatus.Finished, totalTricks + extraTrick, cardsPlayed);
            }

            int cardVal = activeHands[0];
            pile.Add(cardVal);
            activeHands.RemoveAt(0);
            cardsPlayed++;

            if (cardVal > 0)
            {
                currentDebt = cardVal;
                turn = turn == "A" ? "B" : "A";

            }
            else
            {
                if (currentDebt > 0)
                {
                    currentDebt--;
                    if (currentDebt == 0)
                    {
                        otherHands.AddRange(pile);
                        totalTricks++;
                        pile.Clear();

                        if (handA.Count == 0 || handB.Count == 0)
                        {
                            return new GameResult(GameStatus.Finished, totalTricks, cardsPlayed);
                        }
                        turn = turn == "A" ? "B" : "A";
                    }
                }
                else
                {
                    turn = turn == "A" ? "B" : "A";
                }
            }
        }
    }

    private static int GetValue(string card)
    {
        return card switch
        {
            "A" => 4,
            "K" => 3,
            "Q" => 2,
            "J" => 1,
            _ => 0
        };
    }

    private class State
    {
        public List<int> HandA { get; }
        public List<int> HandB { get; }
        public string Turn { get; }

        public State(List<int> handA, List<int> handB, string turn)
        {
            HandA = handA;
            HandB = handB;
            Turn = turn;
        }

        public override bool Equals(object? obj) => obj is State state && HandA.SequenceEqual(state.HandA) && HandB.SequenceEqual(state.HandB) && Turn == state.Turn;
        public override int GetHashCode()
        {
            int hash = 17;
            foreach (var card in HandA)
            {
                hash = hash * 31 + card.GetHashCode();
            }
            foreach (var card in HandB)
            {
                hash = hash * 31 + card.GetHashCode();
            }
            hash = hash * 31 + Turn.GetHashCode();
            return hash;
        }
    }
}
