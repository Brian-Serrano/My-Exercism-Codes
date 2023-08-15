import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

class RelationshipComputer<T> {
    Relationship computeRelationship(List<T> firstList, List<T> secondList) {
        BiPredicate<List<T>, List<T>> isSubList = (first, second) -> IntStream
                .rangeClosed(0, second.size() - first.size())
                .anyMatch(i -> second.subList(i, first.size() + i).equals(first));
        if(firstList.equals(secondList)) return Relationship.EQUAL;
        if(isSubList.test(firstList, secondList)) return Relationship.SUBLIST;
        if(isSubList.test(secondList, firstList)) return Relationship.SUPERLIST;
        return Relationship.UNEQUAL;
    }
}
