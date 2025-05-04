import java.util.ArrayList;
import java.util.List;

class StateOfTicTacToe {
    public GameState determineState(String[] board) {
        if (getPlayerCount(board, 'X') - getPlayerCount(board, 'O') > 1) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        }
        if (getPlayerCount(board, 'X') - getPlayerCount(board, 'O') < 0) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        }
        if (checkWin(board, 'X') && checkWin(board, 'O')) {
            throw new IllegalArgumentException("Impossible board: game should have ended after the game was won");
        }
        if (checkWin(board, 'X') || checkWin(board, 'O')) {
            return GameState.WIN;
        }
        if (!board[0].contains(" ") && !board[1].contains(" ") && !board[2].contains(" ")) {
            return GameState.DRAW;
        }
        return GameState.ONGOING;
    }

    private boolean checkWin(String[] board, char p) {
        List<Boolean> states = new ArrayList<>();
        states.add(checkDiagonalWin(board, p, -1));
        states.add(checkDiagonalWin(board, p, 1));
        for (int i = 0; i < 3; i++) {
            states.add(checkVerticalWin(board, p, i));
            states.add(checkHorizontalWin(board, p, i));
        }
        return states.contains(true);
    }

    private boolean checkVerticalWin(String[] board, char p, int line) {
        return board[0].charAt(line) == p && board[1].charAt(line) == p && board[2].charAt(line) == p;
    }

    private boolean checkHorizontalWin(String[] board, char p, int line) {
        return board[line].equals(String.format("%c%c%c", p, p, p));
    }

    private boolean checkDiagonalWin(String[] board, char p, int direction) {
        return board[0].charAt(1 + direction) == p && board[1].charAt(1) == p && board[2].charAt(1 - direction) == p;
    }

    private int getPlayerCount(String[] board, char p) {
        int count = 0;
        for (String b : board) {
            for (char c : b.toCharArray()) {
                if (c == p) count++;
            }
        }
        return count;
    }
}
