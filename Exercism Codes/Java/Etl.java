import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> newMap = new HashMap<>();
        old.forEach((k, v) -> { for(String x : v) newMap.put(x.toLowerCase(), k); });
        return newMap;
    }
}
