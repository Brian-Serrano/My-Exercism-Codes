public class TwelveDays {
    String[] phrases;
    String[] numberth;

    public TwelveDays(){
        phrases = new String[]{
            "a Partridge in a Pear Tree", "two Turtle Doves", "three French Hens", "four Calling Birds", "five Gold Rings", "six Geese-a-Laying", "seven Swans-a-Swimming", "eight Maids-a-Milking", "nine Ladies Dancing", "ten Lords-a-Leaping", "eleven Pipers Piping", "twelve Drummers Drumming"
        };
        numberth = new String[]{
            "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
        };
    }
    
    public String verse(int verseNumber) {
        String verse = "On the " + numberth[verseNumber - 1] + " day of Christmas my true love gave to me: ";
        for(int i = verseNumber - 1; i >= 0; i--){
            String seperator = i == 0 ? "" : ", ";
            if(i == 0 && verseNumber > 1) verse += "and ";
            verse += phrases[i] + seperator;
        }
        verse += ".\n";
        return verse;
    }

    public String verses(int startVerse, int endVerse) {
        String verse = "";
        for(int i = startVerse; i < endVerse + 1; i++){
        	String seperator = i == endVerse ? "" : "\n";
            verse += verse(i) + seperator;
        }
        return verse;
    }
    
    public String sing() {
        String verse = "";
        for(int i = 0; i < 12; i++){
        	String seperator = i == 11 ? "" : "\n";
            verse += verse(i + 1) + seperator;
        }
        return verse;
    }
}