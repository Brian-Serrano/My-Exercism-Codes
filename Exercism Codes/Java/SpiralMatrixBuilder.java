import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

class SpiralMatrixBuilder {
    int[][] buildMatrixOfSize(int size) {
        int[][] matrix = new int[size][size];
        try {
            matrix[0][0] = 1;
        } catch (IndexOutOfBoundsException e) { return matrix; }
        int funcIdx = 0;
        int[] idx = new int[2];
        List<BiFunction<Integer, Integer, int[]>> funcArr = Arrays.asList(
                (r, c) -> new int[] { r, c + 1 },
                (r, c) -> new int[] { r + 1, c },
                (r, c) -> new int[] { r, c - 1 },
                (r, c) -> new int[] { r - 1, c }
        );
        for(int i = 2; i <= size * size; i++) {
            int[] temp = funcArr.get(funcIdx).apply(idx[0], idx[1]);
            if(checkBounds(temp, size) && matrix[temp[0]][temp[1]] == 0) {
                matrix[temp[0]][temp[1]] = i;
                idx = temp;
            }
            else {
                funcIdx = (funcIdx + 1) % funcArr.size();
                i--;
            }
        }
        return matrix;
    }

    boolean checkBounds(int[] idx, int size) {
        return idx[0] >= 0 && idx[0] < size && idx[1] >= 0 && idx[1] < size;
    }
}
