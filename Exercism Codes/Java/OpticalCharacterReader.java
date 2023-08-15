import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class OpticalCharacterReader {

    String parse(List<String> text) {
        if(text.size() % 4 != 0) throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        if(text.get(0).length() % 3 != 0) throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");

        List<Predicate<List<String>>> readers = Arrays.asList(
                t -> t.equals(Arrays.asList(" _ ", "| |", "|_|", "   ")),
                t -> t.equals(Arrays.asList("   ", "  |", "  |", "   ")),
                t -> t.equals(Arrays.asList(" _ ", " _|", "|_ ", "   ")),
                t -> t.equals(Arrays.asList(" _ ", " _|", " _|", "   ")),
                t -> t.equals(Arrays.asList("   ", "|_|", "  |", "   ")),
                t -> t.equals(Arrays.asList(" _ ", "|_ ", " _|", "   ")),
                t -> t.equals(Arrays.asList(" _ ", "|_ ", "|_|", "   ")),
                t -> t.equals(Arrays.asList(" _ ", "  |", "  |", "   ")),
                t -> t.equals(Arrays.asList(" _ ", "|_|", "|_|", "   ")),
                t -> t.equals(Arrays.asList(" _ ", "|_|", " _|", "   "))
        );

        List<List<List<String>>> cropped = crop(text);
        StringBuilder sb = new StringBuilder();

        for(int j = 0; j < cropped.size(); j++) {
            for(int k = 0; k < cropped.get(j).size(); k++) {
                for(int i = 0; i <= readers.size(); i++) {
                    if(i == readers.size()) sb.append("?");
                    else {
                        if(readers.get(i).test(cropped.get(j).get(k))) {
                            sb.append(i);
                            break;
                        }
                    }
                }
            }
            if(j != cropped.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    List<List<List<String>>> crop(List<String> text) {
        List<List<String>> res = new ArrayList<>();
        Pattern pattern = Pattern.compile(".{3}");
        for(int i = 0; i < text.size(); i += 4) {
            res.add(text.subList(i, i + 4));
        }

        return res.stream().map(r -> zip(r.stream()
                .map(s -> pattern.matcher(s).results()
                        .map(MatchResult::group).toList()).toList()))
                .toList();
    }

    List<List<String>> zip(List<List<String>> input) {
        return IntStream.range(0, input.get(0).size())
                .mapToObj(t -> input.stream().map(l -> l.get(t)).toList())
                .toList();
    }
}