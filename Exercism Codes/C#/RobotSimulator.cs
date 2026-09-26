public enum Direction
{
    North,
    East,
    South,
    West
}

public class RobotSimulator
{
    public RobotSimulator(Direction direction, int x, int y)
    {
        X = x;
        Y = y;
        Direction = direction;
    }

    public Direction Direction { get; set; }

    public int X { get; set; }

    public int Y { get; set; }

    public void Move(string instructions)
    {
        for (int i = 0; i < instructions.Length; i++)
        {
            switch (instructions[i])
            {
                case 'L':
                    TurnLeft();
                    break;
                case 'R':
                    TurnRight();
                    break;
                case 'A':
                    Advance();
                    break;
            }
        }
    }

    private void TurnLeft()
    {
        Direction = Direction switch
        {
            Direction.North => Direction.West,
            Direction.East => Direction.North,
            Direction.South => Direction.East,
            Direction.West => Direction.South,
            _ => throw new NotImplementedException()
        };
    }

    private void TurnRight()
    {
        Direction = Direction switch
        {
            Direction.North => Direction.East,
            Direction.East => Direction.South,
            Direction.South => Direction.West,
            Direction.West => Direction.North,
            _ => throw new NotImplementedException()
        };
    }

    private void Advance()
    {
        (int, int) coord = Direction switch
        {
            Direction.North => (X, Y + 1),
            Direction.East => (X + 1, Y),
            Direction.South => (X, Y - 1),
            Direction.West => (X - 1, Y),
            _ => throw new NotImplementedException()
        };

        X = coord.Item1;
        Y = coord.Item2;
    }
}