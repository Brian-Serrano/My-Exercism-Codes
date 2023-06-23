import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Matrix {
    private List<List<Integer>> values;
    Matrix(List<List<Integer>> values) {
        this.values = values;
    }
    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> saddlePoints = new HashSet<>();
        if (values.isEmpty()) {
            return saddlePoints;
        }
        Integer maxis[] = values.stream()
                .map(list -> list.stream().max(Integer::compareTo).orElse(0))
                .toArray(Integer[]::new);
        List<List<Integer>> cols = IntStream.range(0, values.get(0).size())
                .mapToObj(i -> values.stream().map(list -> list.get(i)).collect(Collectors.toList()))
                .collect(Collectors.toList());
        Integer mins[] = cols.stream()
                .map(col -> col.stream().min(Integer::compareTo).orElse(0))
                .toArray(Integer[]::new);
        for (int i = 0; i < values.size(); i++)
            for (int j = 0; j < cols.size(); j++) {
                int n = values.get(i).get(j);
                if (n >= maxis[i] && n <= mins[j]) {
                    saddlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        return saddlePoints;
    }
}