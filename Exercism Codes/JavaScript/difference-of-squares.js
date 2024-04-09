export class Squares {
    constructor(n) {
        this.n = n;
    }
    get sumOfSquares() {
        return [...new Array(this.n + 1).keys()]
            .reduce((a, c) => a + (c ** 2), 0);
    }
    get squareOfSum() {
        return [...new Array(this.n + 1).keys()]
            .reduce((a, c) => a + c, 0) ** 2;
    }
    get difference() {
        return this.squareOfSum - this.sumOfSquares;
    }
}
