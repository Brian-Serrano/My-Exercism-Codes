//
// This is only a SKELETON file for the 'Scrabble Score' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const score = (w) => {
  const word = w.toLowerCase();
  let points = 0;
  for (const letter of word) {
    if ("aeioulnrst".includes(letter)) {
      points += 1;
    }
    else if ("dg".includes(letter)) {
      points += 2;
    }
    else if ("bcmp".includes(letter)) {
      points += 3;
    }
    else if ("fhvwy".includes(letter)) {
      points += 4;
    }
    else if ("k".includes(letter)) {
      points += 5;
    }
    else if ("jx".includes(letter)) {
      points += 8;
    }
    else if ("qz".includes(letter)) {
      points += 10;
    }
  }

  return points;
};
