import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class React {

    public abstract static class Cell<T> {
        public List<Cell<T>> cells;
        public List<Observer<T>> observers;

        public abstract T getValue();
    }

    public static class InputCell<T> extends Cell<T> {
        private T value;

        public InputCell(T initialValue) {
            value = initialValue;
            observers = new ArrayList<>();
        }

        public void setValue(T newValue) {
            List<T> oldValues = new ArrayList<>();

            for (Observer<T> observer : observers) {
                oldValues.add(observer.obj.getValue());
            }

            value = newValue;

            for (int i = 0; i < observers.size(); i++) {
                T newObservedValue = observers.get(i).obj.getValue();

                if (!Objects.equals(newObservedValue, oldValues.get(i))) {
                    observers.get(i).callback.accept(newObservedValue);
                }
            }
        }

        @Override
        public T getValue() {
            return value;
        }
    }

    public static class ComputeCell<T> extends Cell<T> {
        private final Function<List<T>, T> function;

        public ComputeCell(List<Cell<T>> cells, Function<List<T>, T> function) {
            this.cells = cells;
            this.function = function;
        }

        @Override
        public T getValue() {
            return function.apply(cells.stream().map(Cell::getValue).toList());
        }

        public void addCallback(Consumer<T> callback) {
            add(callback, cells, new HashSet<>());
        }

        private void add(Consumer<T> callback, List<Cell<T>> cells, Set<Cell<T>> visited) {
            for (Cell<T> cell : cells) {
                if (!visited.add(cell)) {
                    continue;
                }

                if (cell instanceof InputCell<?>) {
                    cell.observers.add(new Observer<>(this, callback));
                } else {
                    add(callback, cell.cells, visited);
                }
            }
        }

        public void removeCallback(Consumer<T> callback) {
            remove(callback, cells);
        }

        private void remove(Consumer<T> callback, List<Cell<T>> cells) {
            for (Cell<T> cell : cells) {
                if (cell instanceof InputCell<?>) {
                    List<Consumer<T>> callbacks = cell.observers.stream()
                            .map(c -> c.callback).toList();
                    if (callbacks.contains(callback)) {
                        cell.observers.remove(callbacks.indexOf(callback));
                    }
                }
                else {
                    remove(callback, cell.cells);
                }
            }
        }
    }

    public static class Observer<T> {
        public ComputeCell<T> obj;
        public Consumer<T> callback;

        public Observer(ComputeCell<T> obj, Consumer<T> callback) {
            this.obj = obj;
            this.callback = callback;
        }
    }

    public static <T> InputCell<T> inputCell(T initialValue) {
        return new InputCell<>(initialValue);
    }

    public static <T> ComputeCell<T> computeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
        return new ComputeCell<>(cells, function);
    }
}
