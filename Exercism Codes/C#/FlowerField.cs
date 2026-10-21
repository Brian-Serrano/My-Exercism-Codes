public static class FlowerField
{
    public static string[] Annotate(string[] input)
    {
        string[][] matrix = input.Select(row => row.ToCharArray().Select(c => c.ToString()).ToArray()).ToArray();

        for (int i = 0; i < matrix.Length; i++)
        {
            for (int j = 0; j < matrix[i].Length; j++)
            {
                if (matrix[i][j] != "*")
                {
                    int k = 0;
                    if (i + 1 < matrix.Length && matrix[i + 1][j] == "*") k++;
                    if (i - 1 >= 0 && matrix[i - 1][j] == "*") k++;
                    if (j + 1 < matrix[i].Length && matrix[i][j + 1] == "*") k++;
                    if (j - 1 >= 0 && matrix[i][j - 1] == "*") k++;
                    if (i + 1 < matrix.Length && j + 1 < matrix[i].Length && matrix[i + 1][j + 1] == "*") k++;
                    if (i + 1 < matrix.Length && j - 1 >= 0 && matrix[i + 1][j - 1] == "*") k++;
                    if (i - 1 >= 0 && j + 1 < matrix[i].Length && matrix[i - 1][j + 1] == "*") k++;
                    if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] == "*") k++;

                    if (k > 0)
                        matrix[i][j] = k.ToString();
                }
            }
        }
        return matrix.Select(row => string.Join("", row)).ToArray();
    }
}
