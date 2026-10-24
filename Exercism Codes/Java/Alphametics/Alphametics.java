import java.util.*;
import java.util.stream.Collectors;

class Alphametics {
    private final List<Character> letters;
    private final List<String> words;

    Alphametics(String input) {
        Set<Character> letters = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                letters.add(input.charAt(i));
            }
        }
        this.letters = new ArrayList<>(letters.stream().toList());
        this.words = Arrays.stream(input.split("\\s?\\+\\s?|\\s==\\s")).toList();
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        List<Character> firstLetters = words.stream().map(w -> w.charAt(0)).toList();

        Map<Character, Integer> result = backtrack(new HashMap<>(), letters, words, firstLetters);

        if (result.isEmpty()) {
            throw new UnsolvablePuzzleException();
        }
        else {
            return result;
        }
    }

    Map<Character, Integer> backtrack(Map<Character, Integer> mapping, List<Character> letters, List<String> words, List<Character> firstLetters) throws UnsolvablePuzzleException {
        if (letters.isEmpty()) {
            return mapping;
        }
        char letter = letters.removeFirst();
        for (int i = 0; i < 10; i++) {
            if (mapping.containsValue(i)) {
                continue;
            }
            if (i == 0 && firstLetters.contains(letter)) {
                continue;
            }

            mapping.put(letter, i);

            Map<Character, Integer> newMapping = backtrack(mapping, letters, words, firstLetters);

            if (!newMapping.isEmpty() && isValid(mapping, words)) {
                return newMapping;
            }

            mapping.remove(letter);
        }

        letters.addFirst(letter);

        return new HashMap<>();
    }

    boolean isValid(Map<Character, Integer> mapping, List<String> words) {
        List<Long> numbers = words.stream()
                .map(w -> Long.parseLong(w.chars()
                        .mapToObj(c -> String.valueOf(mapping.get((char)c)))
                        .collect(Collectors.joining())))
                .toList();
        return numbers.subList(0, numbers.size() - 1).stream().reduce(0L, Long::sum).equals(numbers.getLast());
    }
}