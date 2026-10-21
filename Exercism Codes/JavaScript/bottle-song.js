//
// This is only a SKELETON file for the 'Bottle Song' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const numbers = [
    ["One", "no"],
    ["Two", "one"],
    ["Three", "two"],
    ["Four", "three"],
    ["Five", "four"],
    ["Six", "five"],
    ["Seven", "six"],
    ["Eight", "seven"],
    ["Nine", "eight"],
    ["Ten", "nine"],
];

export const recite = (start, take) => {
  const result = [];

  for (let i = start; i > start - take; i--) {
    const n = numbers[i - 1][0];
    const bottle1 = n == "One" ? "bottle" : "bottles";
    result.push(`${n} green ${bottle1} hanging on the wall,`);
    result.push(`${n} green ${bottle1} hanging on the wall,`);

    result.push("And if one green bottle should accidentally fall,");

    const s = numbers[i - 1][1];
    const bottle2 = s == "one" ? "bottle" : "bottles";
    result.push(`There'll be ${s} green ${bottle2} hanging on the wall.`);

    if (i != start - take + 1)
      result.push("");
  }
  return result;
};
