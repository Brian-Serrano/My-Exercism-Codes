public class Transpose {
    public String transpose(String toTranspose) {
        String[] st = padStringArray(toTranspose.split("\n"));
        StringBuilder[] strA = new StringBuilder[st[0].length()];
        for (String s : st) {
            for (int j = 0; j < s.length(); j++) {
                if (strA[j] == null) strA[j] = new StringBuilder();
                strA[j].append(s.charAt(j));
            }
        }
        return String.join("\n", strA);
    }

    public String[] padStringArray(String[] original) {
        for(int i = original.length - 1; i > 0; i--) {
            if(original[i].length() > original[i - 1].length()) {
                int rep = original[i].length() - original[i - 1].length();
                for(int j = 0; j < rep; j++) {
                    original[i - 1] += " ";
                }
            }
        }
        return original;
    }
}
