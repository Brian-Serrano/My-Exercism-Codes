import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LuhnValidator {

    boolean isValid(String candidate) {
        candidate = candidate.replace(" ", "");
        if(!candidate.matches("\\d+") || candidate.length() < 2) return false;
        List<Integer> digits = Arrays
                .stream(candidate.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for(int i = (digits.size() % 2 == 0 ? 0 : 1); i < digits.size(); i += 2) {
            digits.set(i, digits.get(i) * 2);
            if(digits.get(i) > 9)
                digits.set(i, digits.get(i) - 9);
        }
        return digits
                .stream()
                .reduce(Integer::sum)
                .orElse(0) % 10 == 0;
    }

}
