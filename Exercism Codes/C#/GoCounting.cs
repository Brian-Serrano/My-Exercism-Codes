using Xunit.Internal;

public enum Owner
{
    None,
    Black,
    White
}

public class GoCounting
{
    private string[] board;

    public GoCounting(string input)
    {
        board = input.Split("\n");
    }

    public Tuple<Owner, HashSet<(int, int)>> Territory((int, int) coord)
    {
        var territory = GetTerritory(coord.Item1, coord.Item2);
        var playersSurrounding = new List<char>();
        
        foreach (var p in territory)
        {
            var offsets = new (int, int)[]
            {
                (p.Item1 + 1, p.Item2),
                (p.Item1 - 1, p.Item2),
                (p.Item1, p.Item2 + 1),
                (p.Item1, p.Item2 - 1)
            };
            foreach (var o in offsets)
            {
                if (CheckBounds(o) && (board[o.Item2][o.Item1] == 'B' || board[o.Item2][o.Item1] == 'W'))
                {
                    playersSurrounding.Add(board[o.Item2][o.Item1]);
                }
            }
        }

        return Tuple.Create(Contains(playersSurrounding, 'W', 'B') ? Owner.Black : Contains(playersSurrounding, 'B', 'W') ? Owner.White : Owner.None, territory);
    }

    private HashSet<(int, int)> GetTerritory(int x, int y)
    {
        if (!CheckBounds((x, y)))
        {
            throw new ArgumentException();
        }
        return board[y][x] == ' ' ? GetAdjacentPoints(new HashSet<(int, int)>(), (x, y)) : new HashSet<(int, int)>();
    }

    public Dictionary<Owner, HashSet<(int, int)>> Territories()
    {
        var territories = new Dictionary<Owner, HashSet<(int, int)>> {
            [Owner.White] = new HashSet<(int, int)>(),
            [Owner.Black] = new HashSet<(int, int)>(),
            [Owner.None] = new HashSet<(int, int)>()
        };

        for (int i = 0; i < board.Length; i++)
        {
            for (int j = 0; j < board[i].Length; j++)
            {
                var t = Territory((j, i));
                territories[t.Item1].AddRange(t.Item2);
            }
        }

        return territories;
    }

    private HashSet<(int, int)> GetAdjacentPoints(HashSet<(int, int)> points, (int, int) target)
    {
        var offsets = new (int, int)[]
        {
            (target.Item1 + 1, target.Item2),
            (target.Item1 - 1, target.Item2),
            (target.Item1, target.Item2 + 1),
            (target.Item1, target.Item2 - 1)
        };
        points.Add(target);
        foreach (var p in offsets)
        {
            if (CheckBounds(p) && board[p.Item2][p.Item1] == ' ' && !points.Contains(p))
            {
                GetAdjacentPoints(points, p);
            }
        }
        return points;
    }

    private bool Contains(List<char> p, char nc, char c)
    {
        return !p.Contains(nc) && p.Contains(c);
    }

    private bool CheckBounds((int, int) point)
    {
        return point.Item1 >= 0 && point.Item1 < board[0].Length && point.Item2 >= 0 && point.Item2 < board.Length;
    }
}
