public enum ConnectWinner
{
    White,
    Black,
    None
}

public class Connect
{
    private ConnectWinner winner;

    public Connect(string[] input)
    {
        List<List<string>> rows = input.Select(line => line.Trim().Split(' ').ToList()).ToList();

        bool Check(int row, int col, List<List<bool>> visited, char l)
        {
            if (l == 'X' ? col == rows[0].Count : row == rows.Count)
            {
                return true;
            }

            if (!(row >= 0 && row < rows.Count) || !(col >= 0 && col < rows[0].Count) || visited[row][col])
            {
                return false;
            }

            visited[row][col] = true;

            if (rows[row][col][0] == l)
            {
                return Check(row + 1, col, visited, l) ||
                        Check(row - 1, col, visited, l) ||
                        Check(row, col + 1, visited, l) ||
                        Check(row, col - 1, visited, l) ||
                        Check(row - 1, col + 1, visited, l) ||
                        Check(row + 1, col - 1, visited, l);
            }

            return false;
        }

        bool xWins = false;
        bool oWins = false;

        for (int x = 0; x < rows.Count; x++)
        {
            if (rows[x][0] == "X")
            {
                List<List<bool>> visited = rows.Select(r => r.Select(_ => false).ToList()).ToList();
                if (Check(x, 0, visited, 'X'))
                {
                    xWins = true;
                    break;
                }
            }
        }

        for (int o = 0; o < rows[0].Count; o++)
        {
            if (rows[0][o] == "O")
            {
                List<List<bool>> visited = rows.Select(r => r.Select(_ => false).ToList()).ToList();
                if (Check(0, o, visited, 'O'))
                {
                    oWins = true;
                    break;
                }
            }
        }

        if (oWins)
        {
            winner = ConnectWinner.White;
        }
        else
        {
            winner = xWins ? ConnectWinner.Black : ConnectWinner.None;
        }
    }

    public ConnectWinner Result() => winner;
}