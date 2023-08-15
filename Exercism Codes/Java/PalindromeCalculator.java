import java.util.*;

class PalindromeCalculator {

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int start, int end) {
        if(end < start) throw new IllegalArgumentException("invalid input: min must be <= max");
        SortedMap<Long, List<List<Integer>>> sortedMap = new TreeMap<>();
        for (int i = start; i <= end; i++) {
            for (int j = i; j <= end; j++) {
                if (isPalindrome(String.valueOf(i * j))) {
                    sortedMap.computeIfAbsent((long)i * j, k -> new ArrayList<>()).add(Arrays.asList(i, j));
                }
            }
        }
        return sortedMap;
    }

    boolean isPalindrome(String num) {
        return num.contentEquals(new StringBuilder(num).reverse());
    }

}