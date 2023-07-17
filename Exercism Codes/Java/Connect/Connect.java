import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Connect {
    private final String[] board;

    public Connect(String[] board) {
        this.board = Arrays.stream(board).map(a -> a.replaceAll(" ", "")).toArray(String[]::new);
    }

    public Winner computeWinner() {
        if (getIndex('X')
                .stream()
                .anyMatch(a -> checkNeighbors(new Point(0, a), new ArrayList<>(), 'X'))) {
            return Winner.PLAYER_X;
        }
        if (getIndex('O')
                .stream()
                .anyMatch(a -> checkNeighbors(new Point(a, 0), new ArrayList<>(), 'O'))) {
            return Winner.PLAYER_O;
        }

        return Winner.NONE;
    }

    private List<Integer> getIndex(char startChar) {
        List<Integer> indices = new ArrayList<>();
        boolean prevMatched = false;
        int length = startChar == 'X' ? board.length : board[0].length();
        for (int i = 0; i < length; i++) {
            char c = startChar == 'X' ? board[i].charAt(0) : board[0].charAt(i);
            if (c == startChar) {
                if (!prevMatched) {
                    indices.add(i);
                    prevMatched = true;
                }
            } else prevMatched = false;
        }
        return indices;
    }

    private boolean checkNeighbors(Point point, List<Point> prevPoint, char player) {
        if (player == 'X') if (point.x == board[0].length() - 1) return true;
        if (player == 'O') if (point.y == board.length - 1) return true;

        List<Point> pointOffsets = List.of(
                new Point(-1, 0),
                new Point(0, -1),
                new Point(1, 0),
                new Point(0, 1),
                new Point(-1, 1),
                new Point(1, -1)
        );

        for(Point p : pointOffsets) {
            Point nextPoint = new Point(point.x + p.x, point.y + p.y);
            if (checkArrayBound(nextPoint)) {
                if (board[nextPoint.y].charAt(nextPoint.x) == player && !prevPoint.contains(nextPoint)) {
                    prevPoint.add(point);
                    return checkNeighbors(nextPoint, prevPoint, player);
                }
            }
        }

        return false;
    }

    private boolean checkArrayBound(Point point) {
        return point.x >= 0 && point.x < board[0].length() && point.y >= 0 && point.y < board.length;
    }
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point other)) return false;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }
}
