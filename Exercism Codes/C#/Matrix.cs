public class Matrix
{
    private List<List<int>> matrix;

    public Matrix(string input)
    {
        matrix = input.Split("\n").Select(row => row.Split(" ")
            .Select(x => int.Parse(x)).ToList()).ToList();
    }

    public int[] Row(int row)
    {
        return matrix[row - 1].ToArray();
    }

    public int[] Column(int col)
    {
        return matrix.Select(x => x[col - 1]).ToArray();
    }
}