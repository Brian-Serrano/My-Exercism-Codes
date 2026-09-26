public class DndCharacter
{
    public int Strength { get; init; }
    public int Dexterity { get; init; }
    public int Constitution { get; init; }
    public int Intelligence { get; init; }
    public int Wisdom { get; init; }
    public int Charisma { get; init; }
    public int Hitpoints { get; set; }

    public static int Modifier(int score)
    {
        return (int) Math.Floor((score - 10.0) / 2);
    }

    public static int Ability() 
    {
        return Enumerable.Range(0, 4).Select(_ => Random.Shared.Next(1, 7)).Order().Skip(1).Sum();
    }

    public static DndCharacter Generate()
    {
        DndCharacter character = new DndCharacter
        {
            Strength = Ability(),
            Dexterity = Ability(),
            Constitution = Ability(),
            Intelligence = Ability(),
            Wisdom = Ability(),
            Charisma = Ability()
        };
        character.Hitpoints = 10 + Modifier(character.Constitution);
        return character;
    }
}
