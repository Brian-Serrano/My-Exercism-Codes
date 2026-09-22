class SqueakyClean {
    static String clean(String identifier) {
        char[] chrArr = identifier.replaceAll("\\d+", "").toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < chrArr.length; i++) {
            if(Character.isISOControl(chrArr[i])) sb.append("CTRL");
            if(chrArr[i] == 32) sb.append("_");
            if(Character.isLetter(chrArr[i]) && !(chrArr[i] >= '\u03B1' && chrArr[i] <= '\u03C9')) sb.append(chrArr[i]);
            if(chrArr[i] == 45) sb.append(Character.toUpperCase(chrArr[++i]));
        }

        return sb.toString();
    }
}
