import java.util.ArrayList;
import java.util.List;

class PascalsTriangleGenerator {

    int[][] generateTriangle(int size) {
        List<int[]> result = new ArrayList<>();
        int[] currArr = new int[] { 1 };
        for(int i = 0; i < size; i++) {
            int[] arr = new int[i + 1];
            for(int j = 0; j < arr.length; j++) {
                arr[j] = checkBounds(currArr, j) + checkBounds(currArr, j - 1);
            }
            result.add(arr);
            currArr = arr;
        }
        return result.toArray(new int[0][0]);
    }

    int checkBounds(int[] arr, int idx) {
        return idx >= 0 && idx < arr.length ? arr[idx] : 0;
    }
}