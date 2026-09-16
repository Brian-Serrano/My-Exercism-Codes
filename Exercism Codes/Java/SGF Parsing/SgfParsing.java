import java.util.*;
import java.util.stream.Collectors;

class Property {
    public String kind;
    public String label;

    public Property(String kind, String label) {
        this.kind = kind;
        this.label = label;
    }
}

public class SgfParsing {
    public SgfNode parse(String input) throws SgfParsingException {
        if (!input.startsWith("(") || !input.endsWith(")")) {
            throw new SgfParsingException("tree missing");
        }
        input = input.replace("\\[", "[").replace("\\]", "]");
        SgfNode sgf = constructNode(null, input.substring(1, input.length() - 1));
        if (sgf == null) {
            throw new SgfParsingException("tree with no nodes");
        }
        return sgf;
    }

    private SgfNode constructNode(SgfNode node, String input) throws SgfParsingException {
        String matcher = getNode(input);
        List<String> parenthesis = parseParenthesis(input);
        if (matcher != null && !matcher.isEmpty()) {
            node = parseNode(matcher);
            SgfNode childNode = constructNode(node, input.replace(matcher, ""));
            if (childNode != null) {
                node.appendChild(childNode);
            }
            return node;
        }
        if (!parenthesis.isEmpty()) {
            for (String par : parenthesis) {
                String extractPar = par.substring(1, par.length() - 1);
                SgfNode childNode = constructNode(parseNode(extractPar), extractPar);
                if (childNode != null) {
                    node.appendChild(childNode);
                }
            }
        }
        return null;
    }

    private String getNode(String input) {
        if (!input.isEmpty() && input.charAt(0) != ';') {
            return null;
        }

        for (int i = 0; i < input.length(); i++) {
            if (i > 0 && input.substring(i - 1, i + 1).equals("(;")) {
                return input.substring(0, i - 1);
            }
            if (i > 0 && input.substring(i - 1, i + 1).equals("];")) {
                return input.substring(0, i);
            }
        }

        return input;
    }

    private List<String> parseParenthesis(String input) {
        List<String> nodes = new ArrayList<>();
        while (!input.isEmpty()) {
            if (!input.startsWith("(;")) {
                break;
            }

            int counter = 1;

            for (int i = 2; i < input.length(); i++) {
                if (input.startsWith("(;", i)) {
                    counter++;
                }
                else if (input.charAt(i) == ')') {
                    counter--;
                }

                if (counter == 0) {
                    nodes.add(input.substring(0, i + 1));
                    input = input.substring(i + 1);
                }
            }
        }
        return nodes;
    }

    private SgfNode parseNode(String input) throws SgfParsingException {
        Map<String, List<String>> property = new HashMap<>();
        List<Property> matcher = getMatches(!input.isEmpty() ? input.substring(1) : "");
        String key = null;
        for (Property match : matcher) {
            if (match.kind.equals("key")) {
                if (match.label.toUpperCase().equals(match.label)) {
                    key = match.label;
                }
                else {
                    throw new SgfParsingException("property must be in uppercase");
                }
            }
            if (match.kind.equals("value")) {
                property.computeIfAbsent(key, k -> new ArrayList<>()).add(mapPropertyValue(match.label));
            }
        }

        if (key != null && !property.containsKey(key)) {
            throw new SgfParsingException("properties without delimiter");
        }

        return new SgfNode(property);
    }

    private String mapPropertyValue(String input) {
        List<Character> text = new ArrayList<>(input.substring(1, input.length() - 1).chars().mapToObj(c -> (char) c).toList());
        int i = 0;

        while (i < text.size()) {
            if (i > 0 && (text.get(i) == '\\' || text.get(i) == 't' || text.get(i) == 'n') && text.get(i - 1) == '\\') {
                text.remove(i - 1);
                continue;
            }
            if (i > 0 && text.get(i) == '\t' && text.get(i - 1) == '\\') {
                text.remove(i - 1);
                text.remove(i - 1);
                text.add(i - 1, ' ');
                continue;
            }
            if (i > 0 && text.get(i) == '\n' && text.get(i - 1) == '\\') {
                text.remove(i - 1);
                text.remove(i - 1);
                continue;
            }
            i++;
        }

        return text.stream().map(String::valueOf).collect(Collectors.joining()).replaceAll("\\t", " ");
    }

    private List<Property> getMatches(String text) {
        List<Property> result = new ArrayList<>();
        int start = 0;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                if (i == 0) {
                    start = i;
                    continue;
                }

                if (text.charAt(i - 1) == ']' && text.charAt(start) == '[') {
                    if (!hasClosingBeforeOpening(text, i)) {
                        result.add(new Property("value", text.substring(start, i)));
                        start = i;
                        continue;
                    }
                }
            }

            if (text.charAt(i) == '[') {
                if (i > 0 && text.charAt(i - 1) == ']' && text.charAt(start) == '[') {
                    result.add(new Property("value", text.substring(start, i)));
                    start = i;
                    continue;
                }

                if (i > 0 && Character.isLetter(text.charAt(i - 1)) && Character.isLetter(text.charAt(start))) {
                    result.add(new Property("key", text.substring(start, i)));
                    start = i;
                    continue;
                }
            }
        }

        if (!text.isEmpty() && text.charAt(text.length() - 1) == ']') {
            result.add(new Property("value", text.substring(start)));
        }
        if (text.matches("[a-zA-Z]{1,2}")/*!text.isEmpty() && text.length() <= 2 && Character.isLetter(text.charAt(0)) && Character.isLetter(text.charAt(1))*/) {
            result.add(new Property("key", text));
        }

        return result;
    }

    private boolean hasClosingBeforeOpening(String text, int i) {
        for (int x = i; x < text.length(); x++) {
            if (text.charAt(x) == ']') {
                return true;
            }
            if (text.charAt(x) == '[') {
                return false;
            }
        }

        return false;
    }
}

