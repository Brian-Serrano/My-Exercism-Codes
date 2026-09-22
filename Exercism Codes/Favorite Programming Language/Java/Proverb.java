class Proverb {
    public String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        String sentence = "";
        for(int i = 0; i < words.length; i++){
            if(i < words.length - 1){
            sentence += "For want of a " + words[i] + " the " + words[i + 1] + " was lost.\n";
            } else {
                sentence += "And all for the want of a " + words[0] + ".";
            }
        }
        return sentence;
    }

}