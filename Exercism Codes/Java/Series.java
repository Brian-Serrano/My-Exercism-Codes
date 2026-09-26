import java.util.ArrayList;
import java.util.List;

class Series {
    String string;
    Series(String string) {
        this.string = string;
    }

    List<String> slices(int num) {
        if(string.length() < num) throw new IllegalArgumentException("Slice size is too big.");
        if(num < 1) throw new IllegalArgumentException("Slice size is too small.");
        List<String> slicedList = new ArrayList<>();
        for(int i = 0; i <= string.length() - num; i++) {
            slicedList.add(string.substring(i, i + num));
        }
        return slicedList;
    }
}
