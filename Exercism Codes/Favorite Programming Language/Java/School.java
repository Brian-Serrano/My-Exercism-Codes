import java.util.*;

class School {

    SortedMap<Integer, TreeSet<String>> mp = new TreeMap<>();

    void add(String name, int grade) {
        mp.computeIfAbsent(grade, k -> new TreeSet<>()).add(name);
    }

    List<String> roster() {
        return mp.values().stream().flatMap(Collection::stream).toList();
    }

    List<String> grade(int grade) {
        return mp.get(grade) == null ? new ArrayList<>() : mp.get(grade).stream().toList();
    }
}