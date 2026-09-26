public enum Plant
{
    Violets,
    Radishes,
    Clover,
    Grass
}

public class KindergartenGarden
{
    private string[] diagram;

    public KindergartenGarden(string diagram)
    {
        this.diagram = diagram.Split("\n");
    }

    public IEnumerable<Plant> Plants(string student)
    {
        List<Plant> plants = new List<Plant>();
        foreach (string diag in diagram)
        {
            int ch = (student[0] - 65) * 2;
            plants.Add(GetPlant(diag[ch]));
            plants.Add(GetPlant(diag[ch + 1]));
        }
        return plants;
    }

    private Plant GetPlant(char ch)
    {
        return ch switch
        {
            'V' => Plant.Violets,
            'R' => Plant.Radishes,
            'C' => Plant.Clover,
            'G' => Plant.Grass,
            _ => Plant.Violets
        };
    }
}