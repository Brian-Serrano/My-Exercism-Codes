import java.util.Arrays;
import java.util.Collections;

public class EliudsEggs {
    public int eggCount(int number) {
        return Collections.frequency(Arrays.stream(Integer.toBinaryString(number).split("")).toList(), "1");
    }
}
