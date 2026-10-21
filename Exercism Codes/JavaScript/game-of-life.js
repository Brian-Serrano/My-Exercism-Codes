//
// This is only a SKELETON file for the 'Conway's Game of Life' exercise. It's been provided
// as a convenience to get you started writing code faster.
//

export class GameOfLife {
  constructor(matrix) {
    this.m = matrix;
  }

  tick() {
    const elToChange = [];

    for (let i = 0; i < this.m.length; i++) {
      for (let j = 0; j < this.m[i].length; j++) {
        let k = 0;
        if (i + 1 < this.m.length && this.m[i + 1][j] == 1) k++;
        if (i - 1 >= 0 && this.m[i - 1][j] == 1) k++;
        if (j + 1 < this.m[i].length && this.m[i][j + 1] == 1) k++;
        if (j - 1 >= 0 && this.m[i][j - 1] == 1) k++;
        if (i + 1 < this.m.length && j + 1 < this.m[i].length && this.m[i + 1][j + 1] == 1) k++;
        if (i + 1 < this.m.length && j - 1 >= 0 && this.m[i + 1][j - 1] == 1) k++;
        if (i - 1 >= 0 && j + 1 < this.m[i].length && this.m[i - 1][j + 1] == 1) k++;
        if (i - 1 >= 0 && j - 1 >= 0 && this.m[i - 1][j - 1] == 1) k++;

        if (this.m[i][j] == 1 && k < 2 || k > 3)
          elToChange.push([i, j, 0]);
        else if (this.m[i][j] == 0 && k == 3)
          elToChange.push([i, j, 1]);
      }
    }

    for (const el of elToChange)
      this.m[el[0]][el[1]] = el[2];
  }

  state() {
    return this.m;
  }
}
