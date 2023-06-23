class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        String acronym = "";
        String[] words = phrase.split("[[ ]*|[-]*|[_]*]+");

        for (String word : words) {
            acronym += word.charAt(0);
        }
        return acronym.toUpperCase();
    }
}