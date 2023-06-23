import java.util.*;
class ResistorColor {
    int colorCode(String color) {
        if (colors() == null) return -1;
        int len = colors().length;
        int i = 0;
        while (i < len) {
            if (colors()[i] == color) return i;
            else i=i+1;
        }
        return -1;
    }

    String[] colors() {
        String[] color = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
        return color;
    }
}