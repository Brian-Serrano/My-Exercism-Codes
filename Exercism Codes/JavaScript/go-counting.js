//
// This is only a SKELETON file for the 'Go Counting' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const OWNERS = {
  BLACK: "territoryBlack",
  WHITE: "territoryWhite",
  NONE: "territoryNone"
};

export class GoCounting {
  constructor(board) {
    this.board = board;
  }

  getTerritory(x, y) {
    const result = this.territory(x, y);

    if (result.error) {
      return result;
    }
    
    const territory = [...result].toSorted().map(c => this.toArray(c));
    const playersSurrounding = [];

    for (const p of territory) {
      const offsets = [
        [p[0] + 1, p[1]],
        [p[0] - 1, p[1]],
        [p[0], p[1] + 1],
        [p[0], p[1] - 1]
      ];
      for (const o of offsets) {
        if (this.checkBounds(o) && (this.board[o[1]][o[0]] == "B" || this.board[o[1]][o[0]] == "W")) {
          playersSurrounding.push(this.board[o[1]][o[0]]);
        }
      }
    }
    return {
      owner: this.contains(playersSurrounding, 'W', 'B') ? "BLACK" : this.contains(playersSurrounding, 'B', 'W') ? "WHITE" : "NONE",
      territory: territory
    }
  }

  territory(x, y) {
    if (!this.checkBounds([x, y]))
      return { error: 'Invalid coordinate' };
    return this.board[y][x] == " " ? this.getAdjacentPoints(new Set(), [x, y]) : new Set();
  }

  getTerritories() {
    const territories = {
      territoryBlack: new Set(),
      territoryWhite: new Set(),
      territoryNone: new Set()
    };

    for (let i = 0; i < this.board.length; i++) {
      for (let j = 0; j < this.board[i].length; j++) {
        const t = this.getTerritory(j, i);
        for (const u of t.territory) {
          territories[OWNERS[t.owner]].add(this.toString(u));
        }
      }
    }

    return Object.fromEntries(Object.entries(territories).map(([key, value]) => [key,[...value].toSorted().map(c => this.toArray(c))]));
  }

  getAdjacentPoints(points, target) {
    const offsets = [
      [target[0] + 1, target[1]],
      [target[0] - 1, target[1]],
      [target[0], target[1] + 1],
      [target[0], target[1] - 1]
    ];
    points.add(this.toString(target));
    for (const p of offsets) {
      if (this.checkBounds(p) && this.board[p[1]][p[0]] == " " && !points.has(this.toString(p))) {
        this.getAdjacentPoints(points, p);
      }
    }
    return points;
  }

  checkBounds(point) {
    return point[0] >= 0 && point[0] < this.board[0].length && point[1] >= 0 && point[1] < this.board.length;
  }

  contains(p, nc, c) {
    return !p.includes(nc) && p.includes(c);
  }

  toString(point) {
    return `${point[0]},${point[1]}`;
  }

  toArray(str) {
    return str.split(",").map(c => Number(c));
  }
}
