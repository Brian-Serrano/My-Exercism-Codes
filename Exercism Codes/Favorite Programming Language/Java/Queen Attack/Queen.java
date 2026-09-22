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