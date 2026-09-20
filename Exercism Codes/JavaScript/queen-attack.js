//
// This is only a SKELETON file for the 'Queen Attack' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class QueenAttack {
  constructor({
    black: [blackRow, blackColumn] = [],
    white: [whiteRow, whiteColumn] = [],
  } = {}) {
    const br = blackRow ?? 0;
    const bc = blackColumn ?? 3;
    const wr = whiteRow ?? 7;
    const wc = whiteColumn ?? 3;
    
    if (br < 0 || wr < 0 || bc < 0 || wc < 0 || br > 7 || wr > 7 || bc > 7 || wc > 7) {
      throw new Error("Queen must be placed on the board");
    }
    if (br == wr && bc == wc) {
      throw new Error("Queens cannot share the same space");
    }

    this.black = [br, bc];
    this.white = [wr, wc];
  }

  toString() {
    const board = Array.from({ length: 8 }, () => Array(8).fill("_"));
    board[this.black[0]][this.black[1]] = "B";
    board[this.white[0]][this.white[1]] = "W";
    return board.map(b => b.join(" ")).join("\n");
  }

  get canAttack() {
    return this.black[0] == this.white[0] || this.black[1] == this.white[1] || this.black[0] + this.black[1] == this.white[0] + this.white[1] || this.black[0] - this.black[1] == this.white[0] - this.white[1];
  }
}
