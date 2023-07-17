import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Yacht {
    int[] dice;
    YachtCategory yachtCategory;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.dice = dice;
        this.yachtCategory = yachtCategory;
    }

    int score() {
        List<Function<List<Integer>, Integer>> functions = List.of(
                dice -> dice.stream().distinct().count() == 1 ? 50 : 0,
                dice -> dice.stream().mapToInt(d -> d == 1 ? d : 0).sum(),
                dice -> dice.stream().mapToInt(d -> d == 2 ? d : 0).sum(),
                dice -> dice.stream().mapToInt(d -> d == 3 ? d : 0).sum(),
                dice -> dice.stream().mapToInt(d -> d == 4 ? d : 0).sum(),
                dice -> dice.stream().mapToInt(d -> d == 5 ? d : 0).sum(),
                dice -> dice.stream().mapToInt(d -> d == 6 ? d : 0).sum(),
                dice -> Set.of(2, 3).equals(dice.stream().distinct().map(d -> Collections.frequency(dice, d)).collect(Collectors.toSet())) ? dice.stream().reduce(Integer::sum).orElse(0) : 0,
                dice -> dice.stream().distinct().filter(d -> Collections.frequency(dice, d) >= 4).mapToInt(d -> d).sum() * 4,
                dice -> Set.of(1, 2, 3, 4, 5).equals(new HashSet<>(dice)) ? 30 : 0,
                dice -> Set.of(2, 3, 4, 5, 6).equals(new HashSet<>(dice)) ? 30 : 0,
                dice -> dice.stream().mapToInt(d -> d).sum()
        );

        return functions.get(yachtCategory.ordinal()).apply(Arrays.stream(dice).boxed().toList());
    }

}
