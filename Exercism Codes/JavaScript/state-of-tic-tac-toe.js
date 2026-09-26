//
// This is only a SKELETON file for the 'State of Tic Tac Toe' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const gamestate = (board) => {
  const x = getPlayerCount(board, "X");
  const o = getPlayerCount(board, "O");
  const xWins = checkWin(board, "X");
  const oWins = checkWin(board, "O");

  if (x - o > 1) {
    throw new Error("Wrong turn order: X went twice");
  }
  if (x - o < 0) {
    throw new Error("Wrong turn order: O started");
  }
  if ((xWins && x <= o) || (oWins && x > o)) {
    throw new Error("Impossible board: game should have ended after the game was won");
  }
  if (xWins || oWins) {
    return "win";
  }
  if (board.every(x => !x.includes(" "))) {
    return "draw";
  }
  return "ongoing";
};

const checkWin = (board, p) => {
  const states = [checkDiagonalWin(board, p, -1), checkDiagonalWin(board, p, 1)];
  for (let i = 0; i < 3; i++) {
    states.push(checkVerticalWin(board, p, i));
    states.push(checkHorizontalWin(board, p, i));
  }
  return states.includes(true);
};

const checkVerticalWin = (board, p, line) => {
  return board[0][line] == p && board[1][line] == p && board[2][line] == p;
};

const checkHorizontalWin = (board, p, line) => {
  return board[line] == p.repeat(3);
};

const checkDiagonalWin = (board, p, direction) => {
  return board[0][1 + direction] == p && board[1][1] == p && board[2][1 - direction] == p;
};

const getPlayerCount = (board, p) => {
  let count = 0;
  for (const row of board) {
    for (const cell of row) {
      if (cell == p) {
        count++;
      }
    }
  }
  return count;
};