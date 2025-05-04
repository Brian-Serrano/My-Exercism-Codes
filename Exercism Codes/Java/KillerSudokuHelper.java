import java.util.ArrayList;
import java.util.List;

public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        return combinationsInCage(cageSum, cageSize)
                .stream().filter(n -> exclude.stream().noneMatch(n::contains))
                .toList();
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        return generateNumbers(new ArrayList<>(), 0, cageSize)
                .stream().filter(n -> n.stream().reduce(Integer::sum).orElse(0).equals(cageSum))
                .toList();
    }

    List<List<Integer>> generateNumbers(List<Integer> numbers, Integer counter, Integer cageSize) {
        if (counter >= cageSize) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        List<List<Integer>> resultNumbers = new ArrayList<>();
        List<List<Integer>> permutation = generateNumbers(numbers, counter + 1, cageSize);
        for (List<Integer> perm : permutation) {
            for (int i = cageSize - counter; i <= 9; i++) {
                if (perm.isEmpty() || i > perm.getLast()) {
                    List<Integer> temp = new ArrayList<>(perm);
                    temp.add(i);
                    resultNumbers.add(temp);
                }
            }
        }
        return resultNumbers;
    }
}
