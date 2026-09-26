import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        stringToVerify = stringToVerify.replace("-", "");
        int sum = 0;
        if(stringToVerify.length() != 10 ||
                !Pattern.compile("[0-9]*(?:X|$)").matcher(stringToVerify).matches()
        )
            return false;
        else {
            List<Integer> numbers = Arrays
                    .stream(stringToVerify.split(""))
                    .map(s -> s.equals("X") ? 10 : Integer.parseInt(s))
                    .collect(Collectors.toList());
            for(int i = 0; i < 10; i++) {
                sum += numbers.get(i) * (10 - i);
            }
            return sum % 11 == 0;
        }
    }

}
