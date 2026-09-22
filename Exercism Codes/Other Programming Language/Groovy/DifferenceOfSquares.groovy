import java.util.stream.IntStream

class DifferenceOfSquares {

    int num;

    DifferenceOfSquares(int num) {
        this.num = num
    }

    def squareOfSum() {
        int sum = IntStream.range(0, num + 1).sum()
        sum * sum
    }

    def sumOfSquares() {
        IntStream.range(0, num + 1).map(n -> n * n).sum()
    }

    def difference() {
        squareOfSum() - sumOfSquares()
    }

}
