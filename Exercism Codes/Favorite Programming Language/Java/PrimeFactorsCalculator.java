import java.util.ArrayList;
import java.util.List;

class PrimeFactorsCalculator {

    List<Long> calculatePrimeFactorsOf(long number) {
        List<Long> primeFactors = new ArrayList<>();
        for (long i = 2; i <= number; i++)
        {
            while (number % i == 0)
            {
                primeFactors.add(i);
                number /= i;
            }
        }
        return primeFactors;
    }
}

