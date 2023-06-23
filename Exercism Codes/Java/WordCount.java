import java.util.Map;
import java.util.HashMap;

class WordCount {
    public Map<String, Integer> phrase(String input) {
        String[] substring = input.toLowerCase().trim().split("[^a-zA-Z0-9']+");
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (String i : substring) {
            String j = i.replaceAll("^'|'$", "");
            if(j != ""){
                if(!map.containsKey(j)){
                    map.put(j, 1);
                }else {
                    map.put(j, map.get(j) + 1);
                }
            }
        }
        return map;
    }
}