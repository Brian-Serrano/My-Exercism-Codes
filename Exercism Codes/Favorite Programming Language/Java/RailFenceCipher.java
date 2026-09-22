import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RailFenceCipher {
    private final int rails;

    public RailFenceCipher(int rails) {
        this.rails = rails;
    }

    public String getEncryptedData(String input) {
        return createFence(input.length())
                .stream()
                .map(x -> String.valueOf(input.charAt(x)))
                .collect(Collectors.joining());
    }

    public String getDecryptedData(String input) {
        List<Integer> fence = createFence(input.length());
        return IntStream.range(0, input.length())
                .mapToObj(x -> String.valueOf(input.charAt(fence.indexOf(x))))
                .collect(Collectors.joining());
    }

    private List<Integer> createFence(int inputLength) {
        final int cycle = 2 * rails - 2;
        return IntStream.range(0, rails)
                .flatMap(x -> IntStream.range(0, inputLength)
                        .filter(y -> y % cycle == x || y % cycle == cycle - x))
                .boxed()
                .toList();
    }
}