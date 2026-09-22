import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ChangeCalculator {

    List<Integer> coins;

    ChangeCalculator(List<Integer> coins) {
        this.coins = coins;
    }

    List<Integer> computeMostEfficientChange(int total) {
        if(total == 0) return new ArrayList<>();
        if(total < 0) throw new IllegalArgumentException("Negative totals are not allowed.");
        List<List<Integer>> possibleCoins = coins.stream().map(Arrays::asList).toList();
        while(possibleCoins.stream()
                .noneMatch(c -> c.stream().mapToInt(m -> m)
                        .sum() == total)
        ) {
            possibleCoins = possibleCoins.stream()
                    .flatMap(c -> compute(c).stream())
                    .distinct().toList();
            if(possibleCoins.stream()
                    .allMatch(c -> c.stream().mapToInt(m -> m)
                            .sum() > total))
                throw new IllegalArgumentException("The total " + total + " cannot be represented in the given currency.");
        }
        return possibleCoins.stream()
                .filter(c -> c.stream().mapToInt(m -> m).sum() == total)
                .toList().get(0);
    }

    List<List<Integer>> compute(List<Integer> coin) {
        List<List<Integer>> res = new ArrayList<>();
        for(int c : coins) {
            List<Integer> r = new ArrayList<>(coin);
            r.add(c);
            Collections.sort(r);
            res.add(r);
        }
        return res;
    }

}