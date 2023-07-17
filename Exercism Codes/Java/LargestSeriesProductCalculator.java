import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class LargestSeriesProductCalculator {
    private String inputNumber;
    LargestSeriesProductCalculator(String inputNumber) {
        if(Pattern.compile(".*[a-zA-Z].*").matcher(inputNumber).matches()) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
        else {
            this.inputNumber = inputNumber;
        }
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if(numberOfDigits > inputNumber.length())
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        if(numberOfDigits < 0)
            throw new IllegalArgumentException("Series length must be non-negative.");
        List<String> subNumbers = new ArrayList<String>();
        for(int i = 0; i + numberOfDigits <= inputNumber.length(); i++) {
            subNumbers.add(inputNumber.substring(i, i + numberOfDigits));
        }
        return subNumbers
                .stream()
                .map(this::calculateDigits)
                .max(Comparator.comparingLong(a -> a))
                .orElse(0L);
    }

    long calculateDigits(String subNumber) {
        return Arrays
                .stream(subNumber.split(""))
                .map(Long::parseLong)
                .reduce((acc, next) -> acc * next)
                .orElse(0L);
    }
}
