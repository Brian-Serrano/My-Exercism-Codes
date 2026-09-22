//
// This is only a SKELETON file for the 'Yacht' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const categories = ['yacht', 'ones', 'twos', 'threes', 'fours', 'fives', 'sixes', 'full house', 'four of a kind', 'little straight', 'big straight', 'choice'];

export const score = (dice, category) => {
  const functions = [
    x => new Set(x).size == 1 ? 50 : 0,
    x => x.map(d => d == 1 ? d : 0).reduce((x, y) => x + y, 0),
    x => x.map(d => d == 2 ? d : 0).reduce((x, y) => x + y, 0),
    x => x.map(d => d == 3 ? d : 0).reduce((x, y) => x + y, 0),
    x => x.map(d => d == 4 ? d : 0).reduce((x, y) => x + y, 0),
    x => x.map(d => d == 5 ? d : 0).reduce((x, y) => x + y, 0),
    x => x.map(d => d == 6 ? d : 0).reduce((x, y) => x + y, 0),
    x => new Set([2, 3]).symmetricDifference(new Set([...new Set(x)].map(d => x.filter(y => d == y).length))).size == 0 ? x.reduce((x, y) => x + y, 0) : 0,
    x => [...new Set(x)].filter(d => x.filter(y => y == d).length >= 4).reduce((x, y) => x + y, 0) * 4,
    x => new Set([1, 2, 3, 4, 5]).symmetricDifference(new Set(x)).size == 0 ? 30 : 0,
    x => new Set([2, 3, 4, 5, 6]).symmetricDifference(new Set(x)).size == 0 ? 30 : 0,
    x => x.reduce((x, y) => x + y, 0)
  ];

  return functions[categories.indexOf(category)](dice);
};
