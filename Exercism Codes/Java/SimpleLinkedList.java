import java.lang.reflect.Array;
import java.util.*;

class SimpleLinkedList<T> {

    List<T> elements;

    SimpleLinkedList() {
        elements = new ArrayList<>();
    }

    SimpleLinkedList(T[] values) {
        elements = new ArrayList<>(Arrays.asList(values));
    }

    void push(T value) {
        elements.add(value);
    }

    T pop() {
        if(elements.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        return elements.remove(elements.size() - 1);
    }

    void reverse() {
        Collections.reverse(elements);
    }

    int size() {
        return elements.size();
    }

    T peek() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        return elements.get(elements.size() - 1);
    }

    List<T> toList() {
        List<T> result = new ArrayList<>();
        for (int i = elements.size() - 1; i >= 0; i--) {
            result.add(elements.get(i));
        }
        return result;
    }
}
