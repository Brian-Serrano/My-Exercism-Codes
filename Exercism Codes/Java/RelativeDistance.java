import java.util.*;
import java.util.stream.Stream;

class RelativeDistance {

    List<List<String>> familyTree;

    RelativeDistance(Map<String, List<String>>  familyTree) {
        this.familyTree = new ArrayList<>();

        for (var p : familyTree.entrySet()) {
            List<String> t = new ArrayList<>();
            t.add(p.getKey());
            t.addAll(p.getValue());
            this.familyTree.add(t);
        }
    }

    int degreeOfSeparation(String personA, String personB) {
        List<Integer> value = new ArrayList<>();

        find(personA, personB, List.of(personA), value);

        return value.stream().min(Comparator.comparingInt(c -> c)).orElse(-1);
    }

    void find(String person, String personB, List<String> visited, List<Integer> value) {
        if (person.equals(personB)) {
            value.add(visited.size() - 1);
        }

        for (var node : familyTree) {
            if (node.contains(person)) {
                for (var n : node) {
                    if (!person.equals(n) && !visited.contains(n)) {
                        find(n, personB, Stream.concat(visited.stream(), Stream.of(n)).toList(), value);
                    }
                }
            }
        }
    }
}
