public class WordSearch
{
    private string[] grid;

    public WordSearch(string grid)
    {
        this.grid = grid.Split("\n");
    }

    public Dictionary<string, ((int, int), (int, int))?> Search(string[] wordsToSearchFor)
    {
        var result = new Dictionary<string, ((int, int), (int, int))?>();

        for (int x = 0; x < wordsToSearchFor.Length; x++)
        {
            string word = wordsToSearchFor[x];
            int ln = word.Length;
            int rl = grid.Length;
            for (int i = 0; i < rl; i++)
            {
                int cl = grid[i].Length;
                for (int j = 0; j < cl; j++)
                {
                    if (word[0] == grid[i][j])
                    {
                        if (Srch(word, ln <= i + 1, i, j, -1, 0))
                        {
                            result[word] = ((j + 1, i + 1), (j + 1, i - (ln - 1) + 1));
                        }
                        if (Srch(word, ln <= j + 1, i, j, 0, -1))
                        {
                            result[word] = ((j + 1, i + 1), (j - (ln - 1) + 1, i + 1));
                        }
                        if (Srch(word, ln <= rl - i, i, j, 1, 0))
                        {
                            result[word] = ((j + 1, i + 1), (j + 1, i + (ln - 1) + 1));
                        }
                        if (Srch(word, ln <= cl - j, i, j, 0, 1))
                        {
                            result[word] = ((j + 1, i + 1), (j + (ln - 1) + 1, i + 1));
                        }
                        if (Srch(word, ln <= i + 1 && ln <= j + 1, i, j, -1, -1))
                        {
                            result[word] = ((j + 1, i + 1), (j - (ln - 1) + 1, i - (ln - 1) + 1));
                        }
                        if (Srch(word, ln <= i + 1 && ln <= cl - j, i, j, -1, 1))
                        {
                            result[word] = ((j + 1, i + 1), (j + (ln - 1) + 1, i - (ln - 1) + 1));
                        }
                        if (Srch(word, ln <= rl - i && ln <= j + 1, i, j, 1, -1))
                        {
                            result[word] = ((j + 1, i + 1), (j - (ln - 1) + 1, i + (ln - 1) + 1));
                        }
                        if (Srch(word, ln <= rl - i && ln <= cl - j, i, j, 1, 1))
                        {
                            result[word] = ((j + 1, i + 1), (j + (ln - 1) + 1, i + (ln - 1) + 1));
                        }
                    }
                }
            }
            if (!result.ContainsKey(word))
            {
                result[word] = null;
            }
        }
        return result;
    }

    private bool Srch(string word, bool condition, int x, int y, int xOffset, int yOffset)
    {
        if (condition)
        {
            int count = 1;
            for (int i = 1; i < word.Length; i++)
            {
                if (word[i] == grid[x + (i * xOffset)][y + (i * yOffset)])
                {
                    count++;
                }
            }
            return count == word.Length;
        }
        return false;
    }
}