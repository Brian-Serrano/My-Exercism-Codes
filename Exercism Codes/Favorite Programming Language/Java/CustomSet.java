import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CustomSet<T> {

    private final Set<T> set;

    public CustomSet(List<T> lst) {
        set = new HashSet<>(lst);
    }

    public CustomSet(Set<T> set) {
        this.set = set;
    }
    boolean isEmpty() {
        return set.isEmpty();
    }

    boolean contains(T element) {
        return set.contains(element);
    }

    boolean isSubset(CustomSet<T> other) {
        return set.containsAll(other.set);
    }

    boolean isDisjoint(CustomSet<T> other) {
        return Collections.disjoint(set, other.set);
    }

    boolean equals(CustomSet<T> other) {
        return set.equals(other.set);
    }

    void add(T element) {
        set.add(element);
    }

    CustomSet<T> getIntersection(CustomSet<T> other) {
        CustomSet<T> intersection = new CustomSet<>(set);
        intersection.set.retainAll(other.set);
        return intersection;
    }

    CustomSet<T> getDifference(CustomSet<T> other) {
        CustomSet<T> difference = new CustomSet<>(set);
        difference.set.removeAll(other.set);
        return difference;
    }

    CustomSet<T> getUnion(CustomSet<T> other) {
        CustomSet<T> union = new CustomSet<>(set);
        union.set.addAll(other.set);
        return union;
    }
}
