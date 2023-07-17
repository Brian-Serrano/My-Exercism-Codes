public class House {

    private String[] phrases = new String[] {
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
    };

    private String[] words = new String[] {
            "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"
    };

    public String verse(int verse) {
        StringBuilder phraseGroup = new StringBuilder();
        for(int i = verse - 1; i >= 0; i--) {
            phraseGroup.append((i == verse - 1 ? "This is" : "that " + words[i]) + phrases[i]);
        }
        return phraseGroup.toString();
    }

    public String verses(int start, int end) {
        StringBuilder phraseGroups = new StringBuilder();
        for(int i = start; i <= end; i++) {
            phraseGroups.append(verse(i) + (i != end ? "\n" : ""));
        }
        return phraseGroups.toString();
    }

    public String sing() {
        int start = 1;
        int end = 12;
        StringBuilder phraseGroups = new StringBuilder();
        for(int i = start; i <= end; i++) {
            phraseGroups.append(verse(i) + (i != end ? "\n" : ""));
        }
        return phraseGroups.toString();
    }
}
