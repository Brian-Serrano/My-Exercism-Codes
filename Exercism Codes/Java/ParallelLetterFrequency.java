import java.util.HashMap;
import java.util.Map;

class ParallelLetterFrequency {

    char[] input;

    ParallelLetterFrequency(String[] input) {
        this.input = String.join("", input).toLowerCase()
                .replaceAll("[!@#$%^&*()_\\-+={}|\\\\:;\"'<>,.?/\\d\\s]", "")
                .toCharArray();
    }

    Map<Character, Integer> countLetters() {
        Map<Character, Integer> counts = new HashMap<>();
        for (char number : input) {
            counts.put(number, counts.getOrDefault(number, 0) + 1);
        }
        return counts;
    }
}