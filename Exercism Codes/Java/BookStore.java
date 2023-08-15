import java.util.*;

class BookStore {

    double calculateBasketCost(List<Integer> books) {
        int freq = books.stream().distinct()
                .map(b -> Collections.frequency(books, b))
                .max(Comparator.comparingInt(a -> a)).orElse(0);
        long numFreq = books.stream().distinct()
                .filter(b -> Collections.frequency(books, b) == freq).count();
        List<int[]> combinations = findCombinations(
                freq, books.size(), new int[freq], (int) numFreq, new ArrayList<>());
        Map<Integer, Double> discounts = Map
                .of(1, 0.0, 2, 0.4, 3, 0.8, 4, 1.6, 5, 2.0);
        return combinations.stream()
                .map(lst -> Arrays.stream(lst).mapToDouble(b -> b * (8.0 - discounts.get(b)))
                        .sum()).min(Comparator.comparingDouble(a -> a)).orElse(0.0);
    }

    List<int[]> findCombinations(
            int numbers, int total, int[] combination, int startIndex, List<int[]> lst
    ) {
        if (numbers == 0) {
            if (total == 0) lst.add(combination.clone());
        }
        else {
            for (int i = startIndex; i <= 5; i++) {
                combination[combination.length - numbers] = i;
                findCombinations(numbers - 1, total - i, combination, i, lst);
            }
        }
        return lst;
    }
}