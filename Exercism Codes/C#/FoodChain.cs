using System.Text;

public static class FoodChain
{
    private static string[] phrases = [
        "I know an old lady who swallowed a fly.\n" + "I don't know why she swallowed the fly. Perhaps she'll die.",
        "I know an old lady who swallowed a spider.\n" + "It wriggled and jiggled and tickled inside her.\n",
        "I know an old lady who swallowed a bird.\n" + "How absurd to swallow a bird!\n",
        "I know an old lady who swallowed a cat.\n" + "Imagine that, to swallow a cat!\n",
        "I know an old lady who swallowed a dog.\n" + "What a hog, to swallow a dog!\n",
        "I know an old lady who swallowed a goat.\n" + "Just opened her throat and swallowed a goat!\n",
        "I know an old lady who swallowed a cow.\n" + "I don't know how she swallowed a cow!\n",
        "I know an old lady who swallowed a horse.\n" + "She's dead, of course!"
    ];

    private static string[] otherPhrases = [
        "She swallowed the spider to catch the fly.\n" + "I don't know why she swallowed the fly. Perhaps she'll die.",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the cat to catch the bird.\n",
        "She swallowed the dog to catch the cat.\n",
        "She swallowed the goat to catch the dog.\n",
        "She swallowed the cow to catch the goat.\n"
    ];

    public static string Recite(int verseNumber)
    {
        StringBuilder phraseGroup = new StringBuilder();
        if (verseNumber == 8)
            return phraseGroup.Append(phrases[7]).ToString();

        for (int i = verseNumber - 1; i >= 0; i--)
        {
            phraseGroup.Append(i == verseNumber - 1 ? phrases[i] : otherPhrases[i]);
        }
        return phraseGroup.ToString();
    }

    public static string Recite(int startVerse, int endVerse)
    {
        StringBuilder phraseGroups = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++)
        {
            phraseGroups.Append(Recite(i) + (i == endVerse ? "" : "\n\n"));
        }
        return phraseGroups.ToString();
    }
}