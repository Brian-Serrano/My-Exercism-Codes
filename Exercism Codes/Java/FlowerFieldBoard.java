import java.util.List;

class FlowerFieldBoard {

    private final List<String[]> matrix;

    FlowerFieldBoard(List<String> boardRows) {
        matrix = boardRows.stream().map(s -> s.split("")).toList();
    }

    List<String> withNumbers() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).length; j++) {
                if (!matrix.get(i)[j].equals("*")) {
                    int k = 0;
                    if (i + 1 < matrix.size() && matrix.get(i + 1)[j].equals("*")) k++;
                    if (i - 1 >= 0 && matrix.get(i - 1)[j].equals("*")) k++;
                    if (j + 1 < matrix.get(i).length && matrix.get(i)[j + 1].equals("*")) k++;
                    if (j - 1 >= 0 && matrix.get(i)[j - 1].equals("*")) k++;
                    if (i + 1 < matrix.size() && j + 1 < matrix.get(i).length && matrix.get(i + 1)[j + 1].equals("*")) k++;
                    if (i + 1 < matrix.size() && j - 1 >= 0 && matrix.get(i + 1)[j - 1].equals("*")) k++;
                    if (i - 1 >= 0 && j + 1 < matrix.get(i).length && matrix.get(i - 1)[j + 1].equals("*")) k++;
                    if (i - 1 >= 0 && j - 1 >= 0 && matrix.get(i - 1)[j - 1].equals("*")) k++;

                    if (k > 0)
                        matrix.get(i)[j] = String.valueOf(k);
                }
            }
        }

        return matrix.stream().map(s -> String.join("", s)).toList();
    }
}