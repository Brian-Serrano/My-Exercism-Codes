import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IntergalacticTransmission {

    public static List<Integer> getTransmitSequence(List<Integer> message) {
        String binString = message.stream()
                .map(b -> padStart(Integer.toBinaryString(b), 8, '0'))
                .collect(Collectors.joining());
        List<String> result = new ArrayList<>(Pattern.compile(".{1,7}")
                .matcher(binString).results().map(MatchResult::group)
                .toList());

        for (int i = 0; i < result.size(); i++) {
            String padded = padEnd(result.get(i), 7, '0');
            if (Collections.frequency(padded.chars().boxed().toList(), (int)'1') % 2 == 0)
                padded += "0";
            else
                padded += "1";
            result.set(i, padded);
        }
        return result.stream().map(m -> Integer.parseInt(m, 2)).toList();
    }

    public static List<Integer> decodeSequence(List<Integer> sequence) {
        if (sequence.stream().anyMatch(s -> Integer.bitCount(s) % 2 != 0))
            throw new IllegalArgumentException();

        String binString = sequence.stream()
                .map(b -> padStart(Integer.toBinaryString(b), 8, '0'))
                .map(b -> b.substring(0, b.length() - 1))
                .collect(Collectors.joining());
        List<String> result = Pattern.compile(".{8}")
                .matcher(binString).results().map(MatchResult::group)
                .toList();
        return result.stream().map(m -> Integer.parseInt(m, 2)).toList();
    }

    private static String padEnd(String input, int minLength, char padChar) {
        if (input.length() >= minLength) {
            return input;
        }
        return input + String.valueOf(padChar).repeat(minLength - input.length());
    }

    private static String padStart(String input, int minLength, char padChar) {
        if (input.length() >= minLength) {
            return input;
        }
        StringBuilder sb = new StringBuilder(minLength);
        while (sb.length() < minLength - input.length()) {
            sb.append(padChar);
        }
        sb.append(input);
        return sb.toString();
    }
}
