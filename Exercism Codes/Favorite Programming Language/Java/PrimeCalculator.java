import java.util.stream.IntStream;

class PrimeCalculator {

    int nth(int nth) {
        if(nth <= 0) throw new IllegalArgumentException();
        if(nth == 1) return 2;
        int i = 1, count = 1;
        while(count != nth) {
            i += 2;
            if(isPrime(i)) count++;
        }
        return i;
    }

    boolean isPrime(int num) {
        return IntStream.range(2, num).noneMatch(i -> num % i == 0);
    }

}
