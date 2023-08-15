import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BracketChecker {

    private final String brackets;
    private final String myRegex = "(\\{[^\\{\\}\\[\\]\\(\\)]*\\})|(\\([^\\{\\}\\[\\]\\(\\)]*\\))|(\\[[^\\{\\}\\[\\]\\(\\)]*\\])";

    public BracketChecker(String brackets) {
        this.brackets = brackets;
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        Pattern pattern = Pattern.compile(myRegex);
        Matcher matcher = pattern.matcher(brackets);
        String newBracket = brackets;
        while(matcher.find()) {
            newBracket = newBracket.replaceAll(myRegex, "");
            matcher = pattern.matcher(newBracket);
        }
        return newBracket.replaceAll("[^\\{\\}\\[\\]\\(\\)]", "").isEmpty();
    }

}