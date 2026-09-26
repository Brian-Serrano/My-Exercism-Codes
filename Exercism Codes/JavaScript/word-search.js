//
// This is only a SKELETON file for the 'Word Search' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

class WordSearch {
  constructor(grid) {
    this.grid = grid;
  }

  find(words) {
    const result = {};
    for (const word of words) {
      const ln = word.length;
      const rl = this.grid.length;
      for (let i = 0; i < rl; i++) {
        const cl = this.grid[i].length;
        for (let j = 0; j < cl; j++) {
          if (word[0] == this.grid[i][j]) {
            if (this.search(word, ln <= i + 1, i, j, -1, 0)) {
              result[word] = { start: [i + 1, j + 1], end: [i - (ln - 1) + 1, j + 1] };
            }
            if (this.search(word, ln <= j + 1, i, j, 0, -1)) {
              result[word] = { start: [i + 1, j + 1], end: [i + 1, j - (ln - 1) + 1] };
            }
            if (this.search(word, ln <= rl - i, i, j, 1, 0)) {
              result[word] = { start: [i + 1, j + 1], end: [i + (ln - 1) + 1, j + 1] };
            }
            if (this.search(word, ln <= cl - j, i, j, 0, 1)) {
              result[word] = { start: [i + 1, j + 1], end: [i + 1, j + (ln - 1) + 1] };
            }
            if (this.search(word, ln <= i + 1 && ln <= j + 1, i, j, -1, -1)) {
              result[word] = { start: [i + 1, j + 1], end: [i - (ln - 1) + 1, j - (ln - 1) + 1] };
            }
            if (this.search(word, ln <= i + 1 && ln <= cl - j, i, j, -1, 1)) {
              result[word] = { start: [i + 1, j + 1], end: [i - (ln - 1) + 1, j + (ln - 1) + 1] };
            }
            if (this.search(word, ln <= rl - i && ln <= j + 1, i, j, 1, -1)) {
              result[word] = { start: [i + 1, j + 1], end: [i + (ln - 1) + 1, j - (ln - 1) + 1] };
            }
            if (this.search(word, ln <= rl - i && ln <= cl - j, i, j, 1, 1)) {
              result[word] = { start: [i + 1, j + 1], end: [i + (ln - 1) + 1, j + (ln - 1) + 1] };
            }
          }
        }
      }
      if (!(word in result)) {
        result[word] = undefined;
      }
    }
    return result;
  }

  search(word, condition, x, y, xOffset, yOffset) {
    if (condition) {
      let count = 1;
      for (let i = 1; i < word.length; i++) {
        if (word[i] == this.grid[x + (i * xOffset)][y + (i * yOffset)]) {
          count++;
        }
      }
      return count == word.length;
    }
    return false;
  }
}

export default WordSearch;
