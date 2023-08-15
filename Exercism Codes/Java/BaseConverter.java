import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class BaseConverter {

    int base;
    int[] numbers;

    BaseConverter(int base, int[] numbers) {
        if(base < 2) throw new IllegalArgumentException("Bases must be at least 2.");
        if(Arrays.stream(numbers).anyMatch(n -> n >= base))
            throw new IllegalArgumentException("All digits must be strictly less than the base.");
        if(Arrays.stream(numbers).anyMatch(n -> n < 0))
            throw new IllegalArgumentException("Digits may not be negative.");
        this.base = base;
        this.numbers = numbers;
    }

    List<Integer> convertToBase(int base) {
        if(base < 2) throw new IllegalArgumentException("Bases must be at least 2.");
        int dec = IntStream.range(0, numbers.length)
                .reduce(0, (acc, next) -> acc + (numbers[numbers.length - 1 - next] * (int) Math.pow(this.base, next)));
        List<Integer> nums = new ArrayList<>();
        do {
            nums.add(0, dec % base);
            dec /= base;
        }
        while(dec > 0);
        return nums;
    }
}