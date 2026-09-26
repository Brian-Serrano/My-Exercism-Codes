public enum State
{
    Win,
    Draw,
    Ongoing,
    Invalid
}

public class TicTacToe
{
    public TicTacToe(string[] rows)
    {
        int x = GetPlayer(rows, 'X');
        int o = GetPlayer(rows, 'O');
        bool xWins = CheckWin(rows, 'X');
        bool oWins = CheckWin(rows, 'O');

        if ((x - o > 1) || (x - o < 0) || (xWins && x <= o) || (oWins && x > o))
        {
            State = State.Invalid;
            return;
        }
        if (xWins || oWins)
        {
            State = State.Win;
            return;
        }
        if (rows.All(x => !x.Contains(' ')))
        {
            State = State.Draw;
            return;
        }

        State = State.Ongoing;
    }
    
    public State State { get; set; }

    private bool CheckWin(string[] rows, char p)
    {
        List<bool> states = [];
        states.Add(CheckDiagonalWin(rows, p, -1));
        states.Add(CheckDiagonalWin(rows, p, 1));
        for (int i = 0; i < 3; i++)
        {
            states.Add(CheckVerticalWin(rows, p, i));
            states.Add(CheckHorizontalWin(rows, p, i));
        }

        return states.Contains(true);
    }

    private bool CheckVerticalWin(string[] rows, char p, int line)
    {
        return rows[0][line] == p && rows[1][line] == p && rows[2][line] == p;
    }

    private bool CheckHorizontalWin(string[] rows, char p, int line)
    {
        return rows[line] == string.Join("", Enumerable.Repeat(p, 3));
    }

    private bool CheckDiagonalWin(string[] rows, char p, int direction)
    {
        return rows[0][1 + direction] == p && rows[1][1] == p && rows[2][1 - direction] == p;
    }

    private int GetPlayer(string[] rows, char p)
    {
        int count = 0;
        foreach (string row in rows)
        {
            foreach (char cell in row)
            {
                if (cell == p)
                {
                    count++;
                }
            }
        }
        return count;
    }
}
