import java.util.List;
import java.util.ArrayList;

class DoublyLinkedList<T> {
    private Element<T> head = null;
    private Element<T> tail = null;

    void push(T value) {
        Element<T> newElement = new Element<>(value, tail, null);
        if (tail == null) {
            head = newElement;
        } else {
            tail.next = newElement;
        }
        tail = newElement;
    }
    
    T pop() {
        if (tail == null) {
            return null;
        }
        T value = tail.value;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        return value;
    }
    
    void unshift(T value) {
        Element<T> newElement = new Element<>(value, null, head);
        if (head == null) {
            tail = newElement;
        } else {
            head.prev = newElement;
        }
        head = newElement;
    }
    
    T shift() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        return value;
    }
    
    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;
    
        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}