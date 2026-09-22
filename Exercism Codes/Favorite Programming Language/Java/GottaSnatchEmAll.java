import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        return !myCollection.containsAll(theirCollection) && !theirCollection.containsAll(myCollection);
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        return collections.stream().reduce((a, c) -> { Set<String> n = new HashSet<>(a); n.retainAll(c); return n; }).orElse(Set.of());
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream().reduce((a, c) -> { Set<String> n = new HashSet<>(a); n.addAll(c); return n; }).orElse(Set.of());
    }
}
