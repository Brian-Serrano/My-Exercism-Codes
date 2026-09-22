import java.util.ArrayList;
import java.util.List;

class CircularBuffer<T> {

    int buffer;
    List<T> elements;

    CircularBuffer(int buffer) {
        this.buffer = buffer;
        this.elements = new ArrayList<>();
    }

    void write(T data) throws BufferIOException {
        if(elements.size() == buffer) throw new BufferIOException("Tried to write to full buffer");
        elements.add(data);
    }

    void overwrite(T data) {
        if(elements.size() == buffer) clear();
        elements.add(data);
    }

    void clear() {
        if(!elements.isEmpty()) elements.remove(0);
    }

    T read() throws BufferIOException {
        if(elements.isEmpty()) throw new BufferIOException("Tried to read from empty buffer");
        return elements.remove(0);
    }
}