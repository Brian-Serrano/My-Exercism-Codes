using System.Text;

public static class House
{
    private static string[] phrases = [
        " the house that Jack built.",
        " the malt ",
        " the rat ",
        " the cat ",
        " the dog ",
        " the cow with the crumpled horn ",
        " the maiden all forlorn ",
        " the man all tattered and torn ",
        " the priest all shaven and shorn ",
        " the rooster that crowed in the morn ",
        " the farmer sowing his corn ",
        " the horse and the hound and the horn "
    ];

    private static string[] words = [
        "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"
    ];

    public static string Recite(int verseNumber)
    {
        StringBuilder phraseGroup = new StringBuilder();
        for (int i = verseNumber - 1; i >= 0; i--)
        {
            phraseGroup.Append((i == verseNumber - 1 ? "This is" : "that " + words[i]) + phrases[i]);
        }
        return phraseGroup.ToString();
    }

    public static string Recite(int startVerse, int endVerse)
    {
        StringBuilder phraseGroups = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++)
        {
            phraseGroups.Append(Recite(i) + (i < endVerse ? "\n" : ""));
        }
        return phraseGroups.ToString();
    }
}