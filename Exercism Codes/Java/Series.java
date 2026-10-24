import java.util.ArrayList;
import java.util.List;

class Series {
    String string;
    Series(String string) {
        if (string.isEmpty())
            throw new IllegalArgumentException("series cannot be empty");
        this.string = string;
    }

    List<String> slices(int num) {
        if(string.length() < num)
            throw new IllegalArgumentException("slice length cannot be greater than series length");
        if(num < 1)
            throw new IllegalArgumentException("slice length cannot be negative or zero");
        List<String> slicedList = new ArrayList<>();
        for(int i = 0; i <= string.length() - num; i++) {
            slicedList.add(string.substring(i, i + num));
        }
        return slicedList;
    }
}
