class QueenAttackCalculator {
    private final Queen white, black;

    public QueenAttackCalculator(Queen white, Queen black) {
        if(white == null || black == null) throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        if(white.row == black.row && white.column == black.column) throw new IllegalArgumentException("Queens cannot occupy the same position.");
        this.white = white;
        this.black = black;
    }

    public boolean canQueensAttackOneAnother() {
        return white.row == black.row ||
                white.column == black.column ||
                white.row + white.column == black.row + black.column ||
                Math.abs(white.row - white.column) == Math.abs(black.row - black.column);
    }
}

class Queen {
    public int row, column;

    public Queen(int row, int column) {
        if(row < 0) throw new IllegalArgumentException("Queen position must have positive row.");
        if(column < 0) throw new IllegalArgumentException("Queen position must have positive column.");
        if(row > 7) throw new IllegalArgumentException("Queen position must have row <= 7.");
        if(column > 7) throw new IllegalArgumentException("Queen position must have column <= 7.");
        this.row = row;
        this.column = column;
    }
}