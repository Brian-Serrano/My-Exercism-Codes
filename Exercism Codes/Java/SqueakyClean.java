import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SqueakyClean {
    static String clean(String identifier) {
        return toCamelCase(identifier.split(""))
                        .stream()
                        .filter(c -> !c.equals(""))
                        .map(c -> c.equals(" ")?"_":c)
                        .map(c -> Character.isISOControl(c.charAt(0))?"CTRL":c)
                        .map(c -> ((c.charAt(0) >= 'α' && c.charAt(0) <= 'ω') || (!Character.isAlphabetic(c.charAt(0)) && !c.equals("_")))?"":c)
                        .collect(Collectors.joining());
    }
    static List<String> toCamelCase(String[] list){
        int[] indexes = IntStream.range(0, list.length).filter(i -> list[i].equals("-")).toArray();
        for (int i:indexes) list[i+1] = list[i+1].toUpperCase();
        return Arrays.stream(list).filter(c -> !c.equals("-")).collect(Collectors.toList());
    }
}