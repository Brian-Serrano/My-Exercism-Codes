public class PangramChecker {

    public boolean isPangram(String input) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        boolean containsAll = true;

        input = input.toLowerCase().replaceAll("[^a-z]", "");

        for (int i = 0; i < alphabet.length(); i++) {
            if (!input.contains(Character.toString(alphabet.charAt(i)))) {
                containsAll = false;
                break;
            }
        }
        return containsAll;
    }
}