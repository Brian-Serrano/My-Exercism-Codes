import java.util.*;
import java.util.stream.IntStream;

class BookStore {

    double calculateBasketCost(List<Integer> books) {
        Map<Integer, Integer> cnt = getFrequency(books);
        int freq = cnt.values().stream().max(Comparator.comparingInt(x -> x)).orElse(0);
        List<List<Integer>> arr = new ArrayList<>();
        arr.add(new ArrayList<>(Collections.nCopies(freq, 0)));
        List<List<Integer>> combinations = findCombinations(cnt, freq, cnt.keySet().stream().toList(), 0, arr);
        Map<Integer, Double> discounts = Map
                .of(1, 0.0, 2, 0.4, 3, 0.8, 4, 1.6, 5, 2.0);
        return combinations.stream()
                .map(lst -> lst.stream().mapToDouble(b -> b * (8.0 - discounts.get(b)))
                        .sum()).min(Comparator.comparingDouble(a -> a)).orElse(0.0);
    }

    Map<Integer, Integer> getFrequency(List<Integer> books) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int x : books) {
            if (frequency.containsKey(x)) {
                frequency.put(x, frequency.get(x) + 1);
            }
            else {
                frequency.put(x, 1);
            }
        }

        return frequency;
    }

    List<List<Integer>> findCombinations(Map<Integer, Integer> cnt, int freq, List<Integer> nums, int num, List<List<Integer>> arr) {
        if (num == nums.size()) {
            return arr;
        }
        else {
            List<List<Integer>> arr2 = new ArrayList<>(arr);
            arr = new ArrayList<>();

            for (List<Integer> x : arr2) {
                for (List<Integer> positions : getCombinations(IntStream.range(0, freq).boxed().toList(), cnt.get(nums.get(num)))) {
                    List<Integer> newArr = new ArrayList<>(x);
                    for (int pos : positions) {
                        newArr.set(pos, newArr.get(pos) + 1);
                    }
                    arr.add(newArr);
                }
            }

            return findCombinations(cnt, freq, nums, num + 1, arr);
        }
    }

    List<List<Integer>> getCombinations(List<Integer> arr, int r) {
        List<List<Integer>> result = new ArrayList<>();
        return backtrack(0, new ArrayList<>(), result, arr, r);
    }

    List<List<Integer>> backtrack(int start, List<Integer> current, List<List<Integer>> result, List<Integer> arr, int r) {
        if (current.size() == r) {
            result.add(new ArrayList<>(current));
            return result;
        }
        for (int i = start; i < arr.size(); i++) {
            current.add(arr.get(i));
            backtrack(i + 1, current, result, arr, r);
            current.removeLast();
        }

        return result;
    }
}