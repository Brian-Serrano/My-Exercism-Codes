import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Anagram {

    private String word;

    public Anagram(String word) {
        this.word = word;
    }

    public List<String> match(List<String> candidates) {
        return candidates
                .stream()
                .filter(c -> sort(c.toLowerCase())
                        .equals(sort(word.toLowerCase())) &&
                        !c.toLowerCase()
                                .equals(word.toLowerCase()))
                .collect(Collectors.toList());
    }

    public String sort(String word) {
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
