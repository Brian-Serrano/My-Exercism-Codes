import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CryptoSquare {

    String text;

    CryptoSquare(String text) {
        this.text = text;
    }

    String getCiphertext() {
        String normalized = text.toLowerCase().replaceAll("\\W", "");
        int len = normalized.length();
        int c = (int) Math.ceil(Math.sqrt(len));
        int r = (int) Math.floor(Math.sqrt(len));
        if(c * r > len) {
            normalized += " ".repeat(r - (len % r));
        }
        if(c * r < len) {
            normalized += " ".repeat(c - (len % c));
            r++;
        }
        List<String> rect = Pattern.compile(".{" + c + "}")
                .matcher(normalized).results()
                .map(MatchResult::group).toList();
        return IntStream.range(0, rect.get(0).length())
                .mapToObj(idx -> rect.stream()
                        .map(list -> String.valueOf(list.charAt(idx)))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }

}