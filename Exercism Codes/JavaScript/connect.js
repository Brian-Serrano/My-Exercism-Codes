//
// This is only a SKELETON file for the 'Connect' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Board {
  constructor(board) {
    const rows = board.map(x => x.trim().split(" "));

    const check = (row, col, visited, l) => {
      if (l == "X" ? col == rows[0].length : row == rows.length) {
        return true;
      }
      if (!(row >= 0 && row < rows.length) || !(col >= 0 && col < rows[0].length) || visited[row][col]) {
        return false;
      }

      visited[row][col] = true;

      if (rows[row][col] == l) {
        return check(row + 1, col, visited, l) ||
        check(row - 1, col, visited, l) ||
        check(row, col + 1, visited, l) ||
        check(row, col - 1, visited, l) ||
        check(row + 1, col - 1, visited, l) ||
        check(row - 1, col + 1, visited, l);
      }

      return false;
    }

    let xWins = false;
    let oWins = false;

    for (let x = 0; x < rows.length; x++) {
      if (rows[x][0] == "X") {
        const wins = check(x, 0, rows.map(x => x.map(y => false)), "X");
        if (wins) {
          xWins = true;
          break;
        }
      }
    }

    for (let o = 0; o < rows[0].length; o++) {
      if (rows[0][o] == "O") {
        const wins = check(0, o, rows.map(x => x.map(y => false)), "O");
        if (wins) {
          oWins = true;
          break;
        }
      }
    }

    if (oWins) {
      this.win = "O";
    }
    else if (xWins) {
      this.win = "X";
    }
    else {
      this.win = "";
    }
  }

  winner() {
    return this.win;
  }
}