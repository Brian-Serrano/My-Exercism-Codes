export const count = (grid) => {
  let rect = 0;
  for (let i = 0; i < grid.length; i++) {
    for (let j = 0; j < grid[i].length; j++) {
      if (grid[i][j] == '+') {
        for (let k = j + 1; k < grid[i].length; k++) {
          if (grid[i].substring(j, k + 1).match(/^\+[+-]*\+$/)) {
            for (let l = i + 1; l < grid.length; l++) {
              if (grid[l].substring(j, k + 1).match(/^[+|].*[+|]$/)) {
                if (grid[l].substring(j, k + 1).match(/^\+[+-]*\+$/)) rect++;
              }
              else break;
            }
          }
        }
      }
    }
  }
  return rect;
}