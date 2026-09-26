const permutations = function*(arr, r = arr.length) {
  const backtrack = function*(path, used) {
    if (path.length === r) {
      yield [...path];
      return;
    }

    for (let i = 0; i < arr.length; i++) {
      if (used[i]) continue;
      used[i] = true;
      path.push(arr[i]);
      yield* backtrack(path, used);
      path.pop();
      used[i] = false;
    }
  };

  yield* backtrack([], Array(arr.length).fill(false));
};

export const solve = (puzzle) => {
  let lettersSet = new Set();
  let beginningLetters = new Set();

  for (let i = 0; i < puzzle.length; i++) {
    if (/[A-Za-z]/.test(puzzle[i])) {
      if (i === 0 || [" ", '+'].includes(puzzle[i - 1])) {
          beginningLetters.add(puzzle[i]);
      }
      lettersSet.add(puzzle[i]);
    }
  }

  lettersSet = [...lettersSet];

  for (const x of permutations([...Array(10).keys()], lettersSet.length)) {
    const mapping = {};
    let skipFromOuterLoop = false;

    for (let i = 0; i < lettersSet.length; i++) {
      if (beginningLetters.has(lettersSet[i]) && x[i] === 0) {
        skipFromOuterLoop = true;
        break;
      }
      mapping[lettersSet[i]] = x[i];
    }

    if (!skipFromOuterLoop) {
      const mapped = puzzle.split("").map(x => Object.keys(mapping).includes(x) ? mapping[x] : x).join("");
      const numbers = mapped.split(/\s?\+\s?|\s==\s/);
      const leftSide = numbers.slice(0, numbers.length - 1);
      const rightSide = Number(numbers[numbers.length - 1]);
      if (leftSide.reduce((a, b) => a + Number(b), 0) === rightSide) {
        return mapping;
      }
    }
  }

  return null;
};