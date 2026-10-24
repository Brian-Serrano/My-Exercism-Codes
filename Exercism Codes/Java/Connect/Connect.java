import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Connect {

    private final Winner winner;

    public Connect(String[] board) {
        List<List<String>> rows = Arrays.stream(board)
                .map(x -> Arrays.stream(x.trim().split(" ")).toList())
                .toList();

        boolean xWins = false;
        boolean oWins = false;

        for (int x = 0; x < rows.size(); x++) {
            if (rows.get(x).getFirst().equals("X")) {
                List<List<Boolean>> visited = rows.stream().map(row -> row.stream().map(y -> false).collect(Collectors.toList())).toList();
                boolean wins = check(rows, x, 0, visited, "X");
                if (wins) {
                    xWins = true;
                    break;
                }
            }
        }

        for (int o = 0; o < rows.getFirst().size(); o++) {
            if (rows.getFirst().get(o).equals("O")) {
                List<List<Boolean>> visited = rows.stream().map(row -> row.stream().map(y -> false).collect(Collectors.toList())).toList();
                boolean wins = check(rows, 0, o, visited, "O");
                if (wins) {
                    oWins = true;
                    break;
                }
            }
        }

        if (oWins)
            winner = Winner.PLAYER_O;
        else if (xWins)
            winner = Winner.PLAYER_X;
        else
            winner = Winner.NONE;
    }

    public Winner computeWinner() {
        return winner;
    }

    private boolean check(List<List<String>> rows, int row, int col, List<List<Boolean>> visited, String l) {
        if (l.equals("X") ? col == rows.getFirst().size() : row == rows.size()) {
            return true;
        }

        boolean rowInBounds = row >= 0 && row < rows.size();
        boolean columnInBounds = col >= 0 && col < rows.getFirst().size();
        if (!rowInBounds || !columnInBounds || visited.get(row).get(col)) {
            return false;
        }

        visited.get(row).set(col, true);

        if (rows.get(row).get(col).equals(l)) {
            boolean c1 = check(rows, row + 1, col, visited, l);
            boolean c2 = check(rows, row - 1, col, visited, l);
            boolean c3 = check(rows, row, col + 1, visited, l);
            boolean c4 = check(rows, row, col - 1, visited, l);
            boolean c5 = check(rows, row - 1, col + 1, visited, l);
            boolean c6 = check(rows, row + 1, col - 1, visited, l);
            return c1 || c2 || c3 || c4 || c5 || c6;
        }
        return false;
    }
}
