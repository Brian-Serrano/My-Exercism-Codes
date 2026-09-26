import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cipher {

    private final Random random = new Random();
    private String key;

    public Cipher() {
        getKey();
    }

    public Cipher(String key) {
        this.key = key.repeat((int) Math.ceil(100.0 / key.length()));
    }

    public String getKey() {
        return this.key = IntStream.range(0, 100)
                .map(i -> random.nextInt('a', 'z' + 1))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public String encode(String plainText) {
        int[] plainNumbers = plainText.chars().map(c -> c - 'a').toArray();
        int[] keyNumbers = key.substring(0, plainText.length()).chars().map(c -> c - 'a').toArray();
        return IntStream.range(0, plainText.length())
                .map(idx -> (plainNumbers[idx] + keyNumbers[idx]) % 26)
                .mapToObj(c -> String.valueOf((char)(c + 'a')))
                .collect(Collectors.joining());
    }

    public String decode(String cipherText) {
        int[] plainNumbers = cipherText.chars().map(c -> c - 'a').toArray();
        int[] keyNumbers = key.substring(0, cipherText.length()).chars().map(c -> c - 'a').toArray();
        return IntStream.range(0, cipherText.length())
                .map(idx -> ((plainNumbers[idx] - keyNumbers[idx]) + 26) % 26)
                .mapToObj(c -> String.valueOf((char)(c + 'a')))
                .collect(Collectors.joining());
    }
}
