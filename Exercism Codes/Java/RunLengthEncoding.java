import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class RunLengthEncoding {

    String encode(String input) {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= input.length(); i++) {
            if(i < input.length() && input.charAt(i) == input.charAt(i - 1)) {
                count += 1;
            }
            else {
                sb.append(count < 2 ? "" : count).append(input.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }

    String decode(String input) {
        List<String> matches = Pattern.compile("(\\d*[A-Z a-z])")
                .matcher(input).results().map(MatchResult::group).toList();
        StringBuilder sb = new StringBuilder();
        for (String match : matches) {
            int last = match.length() - 1;
            String letter = match.substring(last);
            String number = match.substring(0, last);
            sb.append(letter.repeat(Integer.parseInt(number.isEmpty() ? "1" : number)));
        }
        return sb.toString();
    }

}