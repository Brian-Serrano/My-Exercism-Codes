import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

class SumOfMultiples {

    private final int number;
    private final int[] set;

    SumOfMultiples(int number, int[] set) {
        this.number = number;
        this.set = set;
    }

    int getSum() {
        Predicate<Integer> any = num -> Arrays.stream(set).filter(x -> x != 0).anyMatch(x -> num % x == 0);
        return IntStream.range(1, number).filter(any::test).sum();
    }

}
