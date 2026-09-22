import java.lang.reflect.Array;
import java.util.*;

class SimpleLinkedList<T> {

    List<T> elements;

    SimpleLinkedList() {
        elements = new ArrayList<>();
    }

    SimpleLinkedList(T[] values) {
        elements = Arrays.asList(values);
    }

    void push(T value) {
        elements.add(value);
    }

    T pop() {
        if(elements.isEmpty()) throw new NoSuchElementException();
        return elements.remove(elements.size() - 1);
    }

    void reverse() {
        Collections.reverse(elements);
    }

    @SuppressWarnings("unchecked")
    T[] asArray(Class<T> clazz) {
        Collections.reverse(elements);
        return elements.toArray((T[])Array.newInstance(clazz, elements.size()));
    }

    int size() {
        return elements.size();
    }
}
