export class Matrix {
    constructor(str) {
        this.strRow = str
            .split("\n")
            .map(s => s
                .split(" ")
                .map(c => Number(c))
            );
        this.strCol = this
            .strRow[0]
            .map((_, c) => this.strRow
                .map(r => r[c])
            );
    }
    get rows() {
        return this.strRow;
    }
    get columns() {
        return this.strCol;
    }
}
