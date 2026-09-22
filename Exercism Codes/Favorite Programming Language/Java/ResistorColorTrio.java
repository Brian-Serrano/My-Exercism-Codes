import java.util.List;

class ResistorColorTrio {

    private final List<String> COLORS = List.of("black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white");

    String label(String[] colors) {
        String str = COLORS.indexOf(colors[0]) + "" + COLORS.indexOf(colors[1]) + "0".repeat(COLORS.indexOf(colors[2]));
        long result = Long.parseLong(str);
        if (result >= 1000000000) {
            return (result / 1000000000) + " gigaohms";
        }
        else if (result >= 1000000) {
            return (result / 1000000) + " megaohms";
        }
        else if (result >= 1000) {
            return (result / 1000) + " kiloohms";
        }
        else {
            return result + " ohms";
        }
    }
}
