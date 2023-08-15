import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SgfParsing {
    public SgfNode parse(String input) throws SgfParsingException {
        if(!input.startsWith("(") || !input.endsWith(")")) throw new SgfParsingException("tree missing");
        SgfNode sgf = constructNode(null, input.replace("\\[", "\\\0").replace("\\]", "\\\1").substring(1, input.length() - 1));
        if(sgf == null) throw new SgfParsingException("tree with no nodes");
        return sgf;
    }

    private SgfNode constructNode(SgfNode node, String input) throws SgfParsingException {
        Matcher matcher = Pattern.compile("(^;[^;()]*)").matcher(input);
        List<String> parenthesis = parseParenthesis(input);
        if(matcher.find()) {
            String g = matcher.group();
            node = parseNode(g);
            SgfNode childNode = constructNode(node, input.replace(g, ""));
            if(childNode != null) node.appendChild(childNode);
            return node;
        }
        if(!parenthesis.isEmpty()) {
            for(String par : parenthesis) {
                String extractPar = par.substring(1, par.length() - 1);
                SgfNode childNode = constructNode(parseNode(extractPar), extractPar);
                if(childNode != null) node.appendChild(childNode);
            }
        }
        return null;
    }

    private List<String> parseParenthesis(String input) {
        List<String> nodes = new ArrayList<>();
        while(!input.isEmpty()) {
            if(input.charAt(0) == '(') {
                int counter = 1;
                for(int i = 1; i < input.length(); i++) {
                    if(input.charAt(i) == '(') counter++;
                    if(input.charAt(i) == ')') counter--;
                    if(counter == 0) {
                        nodes.add(input.substring(0, i + 1));
                        input = input.substring(i + 1);
                        break;
                    }
                }
            }
            else return nodes;
        }
        return nodes;
    }

    private SgfNode parseNode(String input) throws SgfParsingException {
        Map<String, List<String>> property = new HashMap<>();
        Matcher matcher = Pattern.compile("(\\w{1,2})(\\[[^\\[\\]]+\\])*").matcher(input);
        while(matcher.find()) {
            String match = matcher.group();
            String regex = "(\\[[^\\[\\]]+\\])";
            List<String> propertyValue = Pattern.compile(regex).matcher(match).results()
                    .map(c -> {
                        String str = c.group().replace("\\\0", "[").replace("\\\1", "]");
                        return str.substring(1, str.length() - 1);
                    }).toList();
            if(propertyValue.isEmpty()) throw new SgfParsingException("properties without delimiter");
            String newInput = match.replaceAll(regex, "");
            if(!newInput.equals(newInput.toUpperCase())) throw new SgfParsingException("property must be in uppercase");
            property.put(newInput, propertyValue);
        }
        return new SgfNode(property);
    }
}
