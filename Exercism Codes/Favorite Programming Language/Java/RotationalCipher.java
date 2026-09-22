class RotationalCipher {
    private int shiftKey;
    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            if (Character.isLetter(c)) {
                c = (char) (c + shiftKey);
                if ((Character.isLowerCase(data.charAt(i)) && c > 'z') || (Character.isUpperCase(data.charAt(i)) && c > 'Z')) {
                    c = (char) (c - 26);
                } else if ((Character.isLowerCase(data.charAt(i)) && c < 'a') || (Character.isUpperCase(data.charAt(i)) && c < 'A')) {
                    c = (char) (c + 26);
                }
            }

            sb.append(c);
        }

        return sb.toString();
    }

}