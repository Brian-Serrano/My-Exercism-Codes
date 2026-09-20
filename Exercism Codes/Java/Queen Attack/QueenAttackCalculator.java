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
                white.row - white.column == black.row - black.column;
    }
}