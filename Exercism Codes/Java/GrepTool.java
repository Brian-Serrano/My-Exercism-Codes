import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class GrepTool {

    String grep(String pattern, List<String> flags, List<String> files) {
        List<List<String>> texts = files.stream().map(f -> {
            try {
                List<String> text = new ArrayList<>();
                File file = new File(f);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    text.add(sc.nextLine());
                }
                sc.close();
                return text;
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException();
            }
        }).toList();

        Pattern patternMatcher = Pattern.compile(
                flags.contains("-x") ? pattern : ".*" + pattern + ".*",
                flags.contains("-i") ? Pattern.CASE_INSENSITIVE : 0);
        BiPredicate<Pattern, String> matcher = (pat, text) -> pat.matcher(text).matches();
        Function<Integer, String> prefix = (num) -> "";
        TriFunction<Stream<Integer>, BiPredicate<Pattern, String>, Function<Integer, String>, Stream<String>> mapper =
                (intStr, match, pref) -> intStr.flatMap(x -> IntStream.range(0, texts.get(x).size())
                        .filter(y -> match.test(patternMatcher, texts.get(x).get(y)))
                        .mapToObj(y -> pref.apply(y + 1) + texts.get(x).get(y))
                        .map(y -> files.size() > 1 ? files.get(x) + ":" + y : y));

        if(flags.contains("-n")) prefix = (num) -> num + ":";
        if(flags.contains("-v")) matcher = (pat, text) -> !(pat.matcher(text).matches());
        if(flags.contains("-l")) {
            mapper = (intStr, match, pref) -> intStr.filter(t -> texts.get(t).stream()
                    .anyMatch(u -> match.test(patternMatcher, u))).map(files::get);
        }
        return mapper.apply(IntStream.range(0, texts.size()).boxed(), matcher, prefix).collect(Collectors.joining("\n"));
    }

}

@FunctionalInterface
interface TriFunction<A,B,C,R> {

    R apply(A a, B b, C c);

    default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
        return (A a, B b, C c) -> after.apply(apply(a, b, c));
    }
}