//
// This is only a SKELETON file for the 'Sum Of Multiples' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const sum = (multiples, limit) => {
  return [...new Array(limit).keys()]
    .filter(x => multiples.some(m => m > 0 ? x % m == 0 : false))
    .reduce((a, b) => a + b, 0);
};
