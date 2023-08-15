import java.util.*;
import java.util.stream.*;

class Matrix {
    
    private final List<List<Integer>> matrix;
    
    Matrix(List<List<Integer>> values) {
        this.matrix = values;
    }
    
    Set<MatrixCoordinate> getSaddlePoints() {
        if (matrix.isEmpty()) return new HashSet<>();
        List<Integer> max = matrix.stream().map(Collections::max).toList();
        List<Integer> min = IntStream.range(0, matrix.get(0).size())
                .map(x -> Collections.min(matrix.stream()
                        .map(y -> y.get(x)).toList()))
                .boxed().toList();
        return IntStream.range(0, matrix.size()).boxed()
                .flatMap(x -> IntStream.range(0, matrix.get(x).size())
                        .filter(y -> matrix.get(x).get(y) >= max.get(x) && matrix.get(x).get(y) <= min.get(y))
                        .mapToObj(y -> new MatrixCoordinate(x + 1, y + 1)))
                .collect(Collectors.toSet());
    }
}