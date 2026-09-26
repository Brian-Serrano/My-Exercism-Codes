class MicroBlog {
    public String truncate(String input) {
        int index = 0;
        int maxLength = 5;
        int count = 0;
        while (count < maxLength && index < input.length()) {
            if (Character.isHighSurrogate(input.charAt(index))) {
                index += 2;
            } else {
                index++;
            }
            count++;
        }
        return input.substring(0, index);
    }
}