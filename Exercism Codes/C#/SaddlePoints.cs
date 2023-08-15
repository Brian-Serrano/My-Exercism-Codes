using System;
using System.Collections.Generic;
using System.Linq;

public static class SaddlePoints
{
    public static IEnumerable<(int, int)> Calculate(int[,] matrix)
    {
        if (matrix.Length == 0) return new List<(int, int)>();
        List<List<int>> newMatrix = ConvertToList(matrix);
        List<int> max = newMatrix.Select(x => x.Max()).ToList();
        List<int> min = Enumerable.Range(0, newMatrix[0].Count)
            .Select(x => newMatrix.Select(y => y[x]))
            .Select(x => x.Min()).ToList();
        return Enumerable.Range(0, newMatrix.Count)
            .SelectMany(x => Enumerable.Range(0, newMatrix[x].Count)
            .Where(y => newMatrix[x][y] >= max[x] && newMatrix[x][y] <= min[y])
            .Select(y => (x + 1, y + 1)));
    }

    private static List<List<int>> ConvertToList(int[,] matrix)
    {
        List<List<int>> newMatrix = new List<List<int>>();
        for(int i = 0; i < matrix.GetLength(0); i++)
        {
            List<int> row = new List<int>();
            for(int j = 0; j < matrix.GetLength(1); j++)
            {
                row.Add(matrix[i, j]);
            }
            newMatrix.Add(row);
        }
        return newMatrix;
    }
}
