import java.util.Map;

class SqueakyClean {
    private static final Map<Character, Character> numbers = Map.of(
            '0', 'o', '1', 'l', '2', 'z',
            '3', 'e', '4', 'a', '5', 's',
            '6', 'd', '7', 't', '8', 'b',
            '9', 'g');

    static String clean(String identifier) {
        char[] chrArr = identifier.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chrArr.length; i++) {
            if (Character.isDigit(chrArr[i])) sb.append(numbers.get(chrArr[i]));
            if (Character.isISOControl(chrArr[i])) sb.append("CTRL");
            if (chrArr[i] == 32) sb.append("_");
            if (Character.isLetter(chrArr[i]) && !(chrArr[i] >= 'α' && chrArr[i] <= 'ω')) sb.append(chrArr[i]);
            if (chrArr[i] == 45) sb.append(Character.toUpperCase(chrArr[++i]));
        }

        return sb.toString();
    }
}
