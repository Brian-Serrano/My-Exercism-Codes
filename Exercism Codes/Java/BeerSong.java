public class BeerSong {

    public String sing(int start, int repeat) {
        StringBuilder song = new StringBuilder();
        for(int i = start, j = 0; j < repeat; i--, j++) {
            song.append((i == 0 ? "No more" : i) + (i == 1 ? " bottle" : " bottles") + " of beer on the wall, " + (i == 0 ? "no more" : i) + (i == 1 ? " bottle" : " bottles") + " of beer.\n" +
                    (i == 0 ? "Go to the store" : "Take " + (i == 1 ? "it" : "one") + " down") + " and " + (i == 0 ? "buy some more, 99" : "pass it around, " + (i == 1 ? "no more" : i - 1)) + (i == 2 ? " bottle" : " bottles") + " of beer on the wall.\n\n");
        }
        return song.toString();
    }

    public String singSong() {
        return sing(99, 100);
    }
}
