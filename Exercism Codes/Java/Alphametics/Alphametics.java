import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Alphametics {

    List<String> words;
    String answer;
    String input;

    Alphametics(String input) {
        this.input = input;
        String[] splitter = input.split(" == ");
        this.words = Arrays.stream(splitter[0].split(" \\+ ")).toList();
        this.answer = splitter[1];
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        List<Character> letters = Pattern.compile("[A-Z]").matcher(input)
                .results().map(c -> c.group().charAt(0)).distinct().toList();
        List<String> firstLetters = new ArrayList<>(words);
        firstLetters.add(answer);
        int[] indices = firstLetters.stream().map(w -> w.charAt(0)).distinct().mapToInt(letters::indexOf).toArray();
        List<byte[]> numbers = IntStream.range(contains(indices, 0) ? 1 : 0, 10).mapToObj(n -> {
            byte[] num = new byte[letters.size()];
            num[0] = (byte) n;
            return num;
        }).toList();
        for(byte i = 1; i < letters.size() - 1; i++) {
            int idx = i;
            numbers = numbers.stream().flatMap(n -> generate(n, idx, (byte) (contains(indices, idx) ? 1 : 0)).stream()).toList();
        }
        byte start = (byte) (contains(indices, letters.size() - 1) ? 1 : 0);
        for(byte[] number : numbers) {
            for(byte i = start; i < 10; i++) {
                if(!contains(number, i, number.length - 1)) {
                    number[number.length - 1] = i;
                    if(isValid(letters, number)) {
                        return IntStream.range(0, letters.size()).boxed()
                                .collect(Collectors.toMap(letters::get, v -> (int) number[v]));
                    }
                }
            }
        }
        throw new UnsolvablePuzzleException();
    }

    List<byte[]> generate(byte[] num, int idx, byte start) {
        List<byte[]> result = new ArrayList<>();
        for(byte i = start; i < 10; i++) {
            if(!contains(num, i, idx)) {
                byte[] n = num.clone();
                n[idx] = i;
                result.add(n);
            }
        }
        return result;
    }

    boolean contains(byte[] num, byte n, int idx) {
        for(byte i = 0; i < idx; i++) if(num[i] == n) return true;
        return false;
    }

    boolean contains(int[] num, int n) {
        for(byte i = 0; i < num.length; i++) if(num[i] == n) return true;
        return false;
    }

    boolean isValid(List<Character> letters, byte[] number) {
        long numbersSum = words.stream().mapToLong(w -> w.chars()
                .mapToLong(c -> number[letters.indexOf((char) c)])
                .reduce(0L, (a, n) -> 10 * a + n)).sum();
        long ans = answer.chars().mapToLong(c -> number[letters.indexOf((char) c)])
                .reduce(0L, (a, n) -> 10 * a + n);
        return numbersSum == ans;
    }

}