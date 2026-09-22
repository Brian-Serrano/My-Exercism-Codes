using System;
using System.Collections.Generic;
using System.Linq;
public class HighScores
{
    private readonly List<int> list;
    public HighScores(List<int> list) => this.list = list;
    public List<int> Scores() => list;
    public int Latest() => list.Last();
    public int PersonalBest() => list.Max();
    public List<int> PersonalTopThree() => list.OrderByDescending(x => x).Take(3).ToList();
}