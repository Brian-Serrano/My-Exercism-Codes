import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsogramChecker {

    public boolean isIsogram(String phrase) {
        return !Arrays.stream(phrase.toLowerCase().split(""))
        		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        		.entrySet()
        		.stream()
        		.anyMatch(entry -> entry.getValue() >= 2 && !entry.getKey().equals(" ") && !entry.getKey().equals("-"));
    }
}