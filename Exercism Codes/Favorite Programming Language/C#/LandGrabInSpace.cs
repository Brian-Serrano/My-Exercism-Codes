using System;
using System.Collections.Generic;

public struct Coord : IComparable<Coord>
{
    public Coord(ushort x, ushort y)
    {
        X = x;
        Y = y;
    }

    public ushort X { get; }
    public ushort Y { get; }

    public int CompareTo(Coord other)
    {
        return (X - other.X) + (Y - other.Y);
    }
}

public struct Plot : IComparable<Plot>
{
    public Plot(Coord c1, Coord c2, Coord c3, Coord c4)
    {
        C1 = c1;
        C2 = c2;
        C3 = c3;
        C4 = c4;
    }

    public Coord C1 { get; }
    public Coord C2 { get; }
    public Coord C3 { get; }
    public Coord C4 { get; }

    public int CompareTo(Plot other)
    {
        return C1.CompareTo(other.C1) + C2.CompareTo(other.C2) + C3.CompareTo(other.C3) + C4.CompareTo(other.C4);
    }
}


public class ClaimsHandler
{
    private readonly List<Plot> plots = new();

    public void StakeClaim(Plot plot)
    {
        plots.Add(plot);
    }

    public bool IsClaimStaked(Plot plot)
    {
        return plots.Contains(plot);
    }

    public bool IsLastClaim(Plot plot)
    {
        return plots[^1].Equals(plot);
    }

    public Plot GetClaimWithLongestSide()
    {
        List<Plot> newPlots = plots;
        newPlots.Sort();
        return newPlots[^1];
    }
}
