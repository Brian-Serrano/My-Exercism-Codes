import java.util.*;

class School {

    SortedMap<Integer, TreeSet<String>> students = new TreeMap<>();

    boolean add(String name, int grade) {
        if (!roster().contains(name)) {
            return students.computeIfAbsent(grade, k -> new TreeSet<>()).add(name);
        }
        else {
            return false;
        }
    }

    List<String> roster() {
        return students.values().stream().flatMap(Collection::stream).toList();
    }

    List<String> grade(int grade) {
        return students.get(grade) == null ? new ArrayList<>() : students.get(grade).stream().toList();
    }
}