//
// This is only a SKELETON file for the 'Line Up' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const format = (name, number) => {
  let ending = "th";
  const n = number.toString();
  const l = n.length;
  const a = l > 1 && n[l - 2] == "1";

  if (n[l - 1] == "1" && !a)
    ending = "st";
  if (n[l - 1] == "2" && !a)
    ending = "nd";
  if (n[l - 1] == "3" && !a)
    ending = "rd";

  return `${name}, you are the ${n}${ending} customer we serve today. Thank you!`;
};
