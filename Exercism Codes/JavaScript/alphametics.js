export const solve = (puzzle) => {
  const letters = [...new Set(puzzle.split("").filter(x => /^[a-z]$/i.test(x)))];
  const words = puzzle.split(/\s?\+\s?|\s==\s/);
  const firstLetters = words.map(x => x[0]);
  const lastLetters = words.map(x => x[x.length - 1]);
  lastLetters.pop();
  const answer = words[words.length - 1];
  
  letters.splice(letters.indexOf(answer[answer.length - 1]), 1);
  letters.unshift(answer[answer.length - 1]);

  const lettersToMove = [];

  for (const letter of letters) {
    if (letter == answer[answer.length - 1] || lastLetters.includes(letter)) {
      lettersToMove.push(letter);
    }
  }

  for (const letter of lettersToMove) {
    letters.splice(letters.indexOf(letter), 1);
    letters.unshift(letter);
  }
  
  const result = backtrack({}, letters, words, firstLetters, lastLetters, answer, 0, Array(10).fill(false));
  return Object.keys(result).length > 0 ? result : null;
};

const backtrack = (mapping, letters, words, firstLetters, lastLetters, answer, idx, usedDigits) => {
  if (idx == letters.length) {
    return mapping;
  }

  const letter = letters[idx];

  for (let i = 0; i < 10; i++) {
    if (usedDigits[i]) {
      continue;
    }
    if (i == 0 && firstLetters.includes(letter)) {
      continue;
    }

    mapping[letter] = i;
    usedDigits[i] = true;
    
    if (letter == answer[answer.length - 1]) {
      const sum = lastLetters.map(l => mapping[l]).reduce((a, b) => a + b, 0);
      const lastDigit = sum % 10;
      
      if (lastDigit != i) {
        delete mapping[letter];
        usedDigits[i] = false;
        continue;
      }
    }
    const newMapping = backtrack(mapping, letters, words, firstLetters, lastLetters, answer, idx + 1, usedDigits);

    if (Object.keys(newMapping).length > 0 && isValid(mapping, words)) {
      return newMapping;
    }
    delete mapping[letter];
    usedDigits[i] = false;
  }
  return {};
};

const isValid = (mapping, words) => {
  const numbers = words.map(w => Number(w.split("").map(c => mapping[c]).join("")));
  return numbers.slice(0, numbers.length - 1)
    .reduce((a, b) => a + b, 0) === 
    numbers[numbers.length - 1];
}