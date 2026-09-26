import java.util.*;
import java.util.stream.*;

class DiamondPrinter {

    public List<String> printToList(char letter) {
        int size = 2 * (letter - 'A') + 1;
        return reflect(IntStream.range(0, size / 2 + 1).mapToObj(idx -> {
            char c = (char) ('A' + idx);
            char[] buffer = " ".repeat(size).toCharArray();
            int leftPosition = size / 2 + 1 - idx - 1;
            buffer[leftPosition] = c;
            buffer[size - leftPosition - 1] = c;
            return new String(buffer);
        }).collect(Collectors.toCollection(ArrayList::new)));
    }

    private List<String> reflect(List<String> lst) {
        lst.addAll(IntStream.range(1, lst.size()).mapToObj(i -> lst.get(lst.size() - i - 1)).toList());
        return lst;
    }

}