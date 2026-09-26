public class FoodChain {

    private String[] phrases = new String[] {
            "I know an old lady who swallowed a fly.\n" + "I don't know why she swallowed the fly. Perhaps she'll die.",
            "I know an old lady who swallowed a spider.\n" + "It wriggled and jiggled and tickled inside her.\n",
            "I know an old lady who swallowed a bird.\n" + "How absurd to swallow a bird!\n",
            "I know an old lady who swallowed a cat.\n" + "Imagine that, to swallow a cat!\n",
            "I know an old lady who swallowed a dog.\n" + "What a hog, to swallow a dog!\n",
            "I know an old lady who swallowed a goat.\n" + "Just opened her throat and swallowed a goat!\n",
            "I know an old lady who swallowed a cow.\n" + "I don't know how she swallowed a cow!\n",
            "I know an old lady who swallowed a horse.\n" + "She's dead, of course!"
    };

    private String[] otherPhrases = new String[] {
            "She swallowed the spider to catch the fly.\n" + "I don't know why she swallowed the fly. Perhaps she'll die.",
            "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
            "She swallowed the cat to catch the bird.\n",
            "She swallowed the dog to catch the cat.\n",
            "She swallowed the goat to catch the dog.\n",
            "She swallowed the cow to catch the goat.\n"
    };

    public String verse(int verse) {
        StringBuilder phraseGroup = new StringBuilder();
        if(verse > 7) return phraseGroup.append(phrases[7]).toString();
        for(int i = verse - 1; i >= 0; i--) {
            if(i == verse - 1) {
                phraseGroup.append(phrases[i]);
            }
            else {
                phraseGroup.append(otherPhrases[i]);
            }
        }
        return phraseGroup.toString();
    }

    public String verses(int start, int end) {
        StringBuilder phraseGroups = new StringBuilder();
        for(int i = start; i <= end; i++) {
            phraseGroups.append(verse(i) + (i != end ? "\n\n" : ""));
        }
        return phraseGroups.toString();
    }
}
