import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ParallelLetterFrequency {

    char[] input;

    ParallelLetterFrequency(String input) {
        this.input = input.toLowerCase().replaceAll("[^a-z]", "").toCharArray();
    }

    Map<Integer, Integer> letterCounts() {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int number : input) {
            counts.put(number, counts.getOrDefault(number, 0) + 1);
        }
        return counts;
    }
}