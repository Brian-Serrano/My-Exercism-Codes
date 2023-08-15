using System;

public class Queen
{
    public Queen(int row, int column)
    {
        Row = row;
        Column = column;
    }

    public int Row { get; }
    public int Column { get; }
}

public static class QueenAttack
{
    public static bool CanAttack(Queen white, Queen black) => white.Row == black.Row ||
            white.Column == black.Column ||
            ((white.Row + white.Column == black.Row + black.Column ||
            Math.Abs(white.Row - white.Column) == Math.Abs(black.Row - black.Column)) &&
            Math.Abs(white.Row - black.Row) == Math.Abs(white.Column - black.Column));

    public static Queen Create(int row, int column) => row < 0 || row >= 8 || column < 0 || column >= 8 ? throw new ArgumentOutOfRangeException() : new(row, column);
}