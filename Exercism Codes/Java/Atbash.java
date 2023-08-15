import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Atbash {

    String encode(String input) {
        return Pattern.compile(".{1,5}").matcher(reverse(input))
                .results().map(MatchResult::group)
                .collect(Collectors.joining(" "));
    }

    String decode(String input) {
        return reverse(input);
    }

    String reverse(String input) {
        return input.toLowerCase().replaceAll("\\W", "")
                .chars().map(c -> c >= '0' && c <= '9' ? c - 'a' : 26 - (c - 'a') - 1)
                .mapToObj(c -> String.valueOf((char)(c + 'a')))
                .collect(Collectors.joining());
    }
}