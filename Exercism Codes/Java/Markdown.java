class Markdown {

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        boolean activeList = false;

        for (String line : lines) {
            String theLine = parseHeader(line);
            if (theLine == null) theLine = parseListItem(line);
            if (theLine == null) theLine = parseParagraph(line);

            if (theLine.matches("(<li>).*") && !activeList) {
                activeList = true;
                result.append("<ul>");
            }
            if (!theLine.matches("(<li>).*") && activeList) {
                activeList = false;
                result.append("</ul>");
            }
            result.append(theLine);
        }
        if (activeList) result.append("</ul>");
        return result.toString();
    }

    private String parseHeader(String markdown) {
        int count = 0;
        while(markdown.charAt(count) == '#') count++;
        if (count == 0) return null;
        return "<h" + count + ">" + parseSomeSymbols(markdown.substring(count + 1)) + "</h" + count + ">";
    }

    private String parseListItem(String markdown) {
        if (!markdown.startsWith("*")) return null;
        return "<li>" + parseSomeSymbols(markdown.substring(2)) + "</li>";
    }

    private String parseParagraph(String markdown) {
        return "<p>" + parseSomeSymbols(markdown) + "</p>";
    }

    private String parseSomeSymbols(String markdown) {
        return markdown.replaceAll("__(.+)__", "<strong>$1</strong>").replaceAll("_(.+)_", "<em>$1</em>");
    }
}
