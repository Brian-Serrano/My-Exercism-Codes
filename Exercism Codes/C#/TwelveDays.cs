public static class TwelveDays
{
    private static string[] phrases = new string[]
    {
        "a Partridge in a Pear Tree",
        "two Turtle Doves",
        "three French Hens",
        "four Calling Birds",
        "five Gold Rings",
        "six Geese-a-Laying",
        "seven Swans-a-Swimming",
        "eight Maids-a-Milking",
        "nine Ladies Dancing",
        "ten Lords-a-Leaping",
        "eleven Pipers Piping",
        "twelve Drummers Drumming"
    };

    private static string[] number = new string[]
    {
        "first",
        "second",
        "third",
        "fourth",
        "fifth",
        "sixth",
        "seventh",
        "eighth",
        "ninth",
        "tenth",
        "eleventh",
        "twelfth"
    };

    public static string Recite(int verseNumber)
    {
        string verse = $"On the {number[verseNumber - 1]} day of Christmas my true love gave to me: ";
        for (int i = verseNumber - 1; i >= 0; i--)
        {
            string seperator = i == 0 ? "" : ", ";
            if (i == 0 && verseNumber > 1)
            {
                verse += "and ";
            }
            verse += phrases[i] + seperator;
        }
        verse += ".";
        return verse.TrimEnd(',', ' ');
    }

    public static string Recite(int startVerse, int endVerse)
    {
        string verses = "";
        for (int i = startVerse; i <= endVerse; i++)
        {
            verses += Recite(i) + "\n";
        }
        return verses.TrimEnd('\n');
    }
}