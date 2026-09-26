import java.util.stream.IntStream;

public class NaturalNumber {

    private final int number;

    public NaturalNumber(int number) {
        if(number < 1) throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        this.number = number;
    }

    public Classification getClassification() {
        int aliquot = IntStream.range(1, number).filter(x -> number % x == 0).sum();
        return aliquot > number ? Classification.ABUNDANT : aliquot == number ? Classification.PERFECT : Classification.DEFICIENT;
    }
}
