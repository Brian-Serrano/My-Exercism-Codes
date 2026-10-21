//
// This is only a SKELETON file for the 'Killer Sudoku Helper' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const combinations = (cage) => {
  return getCombinations([1, 2, 3, 4, 5, 6, 7, 8, 9], cage.size)
    .filter(i => i.reduce((a, b) => a + b, 0) == cage.sum && !i.some(x => cage.exclude.includes(x)));
};

const getCombinations = (arr, r) => {
  const results = [];

  const backtrack = (start, path) => {
    if (path.length === r) {
      results.push([...path]);
      return;
    }

    for (let i = start; i < arr.length; i++) {
      path.push(arr[i]);
      backtrack(i + 1, path);
      path.pop();
    }
  }

  backtrack(0, []);
  return results;
};