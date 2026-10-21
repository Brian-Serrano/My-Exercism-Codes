public class RelativeDistance
{   
    private List<List<string>> familyTree;

    public RelativeDistance(Dictionary<string, string[]> familyTree)
    {
        this.familyTree = familyTree.Select(p =>
        {
            var relatives = new List<string> { p.Key };
            relatives.AddRange(p.Value);
            return relatives;
        }).ToList();
    }
    
    public int DegreeOfSeparation(string personA, string personB)
    {
        List<int> value = new List<int>();

        void Find(string person, List<string> visited)
        {
            if (person == personB)
            {
                value.Add(visited.Count - 1);
                return;
            }
            foreach (var node in familyTree)
            {
                if (node.Contains(person))
                {
                    foreach (var n in node)
                    {
                        if (person != n && !visited.Contains(n))
                        {
                            Find(n, [..visited, n]);
                        }
                    }
                }
            }
        }

        Find(personA, [personA]);

        return value.Count > 0 ? value.Min() : -1;
    }
}
