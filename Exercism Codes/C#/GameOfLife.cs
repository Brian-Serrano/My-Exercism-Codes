using System;

public static class GameOfLife
{
    public static int[,] Tick(int[,] matrix)
    {
        List<List<int>> elToChange = new List<List<int>>();

        for (int i = 0; i < matrix.GetLength(0); i++)
        {
            for (int j = 0; j < matrix.GetLength(1); j++)
            {
                int k = 0;
                if (i + 1 < matrix.GetLength(0) && matrix[i + 1, j] == 1) k++;
                if (i - 1 >= 0 && matrix[i - 1, j] == 1) k++;
                if (j + 1 < matrix.GetLength(1) && matrix[i, j + 1] == 1) k++;
                if (j - 1 >= 0 && matrix[i, j - 1] == 1) k++;
                if (i + 1 < matrix.GetLength(0) && j + 1 < matrix.GetLength(1) && matrix[i + 1, j + 1] == 1) k++;
                if (i + 1 < matrix.GetLength(0) && j - 1 >= 0 && matrix[i + 1, j - 1] == 1) k++;
                if (i - 1 >= 0 && j + 1 < matrix.GetLength(1) && matrix[i - 1, j + 1] == 1) k++;
                if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1, j - 1] == 1) k++;

                if (matrix[i, j] == 1 && (k < 2 || k > 3))
                {
                    elToChange.Add(new List<int> { i, j, 0 });
                }
                else if (matrix[i, j] == 0 && k == 3)
                {
                    elToChange.Add(new List<int> { i, j, 1 });
                }
            }
        }

        foreach (var el in elToChange)
        {
            matrix[el[0], el[1]] = el[2];
        }
        return matrix;
    }
}
