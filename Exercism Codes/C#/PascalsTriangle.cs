using System;
using System.Collections.Generic;

public static class PascalsTriangle
{
    public static IEnumerable<IEnumerable<int>> Calculate(int rows)
    {
        int[] prevArr = new int[] { 1 };
        List<int[]> result = new List<int[]>();
        for(int i = 0; i < rows; i++)
        {
            int[] arr = new int[i + 1];
            for(int j = 0; j < arr.Length; j++)
            {
                arr[j] = checkBounds(prevArr, j) + checkBounds(prevArr, j - 1);
            }
            result.Add(arr);
            prevArr = arr;
        }
        return result;
    }

    private static int checkBounds(int[] arr, int idx) => idx < arr.Length && idx >= 0 ? arr[idx] : 0;

}