import java.util.ArrayList;
import java.util.List;

class BinarySearch {
    private List<Integer> arr;
    public BinarySearch(List<Integer> arr) {
        this.arr = arr;
    }
    public int indexOf(int num) throws ValueNotFoundException {
        int res = -1;
        for(int i=0; i<arr.size(); i++) {
            if(arr.get(i) == num) {
                res = arr.indexOf(num);
            }
        }
        if(res == -1) {
            throw new ValueNotFoundException("Value not in array");
        } else {
            return res;
        }
    }
}