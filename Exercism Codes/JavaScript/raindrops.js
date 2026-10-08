//
// This is only a SKELETON file for the 'Raindrops' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const convert = (number) => {
  let pl = "";
  if (number % 3 == 0)
    pl += "Pling";
  if (number % 5 == 0)
    pl += "Plang";
  if (number % 7 == 0)
    pl += "Plong";
  return pl.length > 0 ? pl : `${number}`;
};
