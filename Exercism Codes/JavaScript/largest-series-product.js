//
// This is only a SKELETON file for the 'Largest Series Product' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const largestProduct = (series, len) => {
  if (/.*[a-zA-Z].*/.test(series))
    throw new Error("digits input must only contain digits");
  if (len > series.length)
    throw new Error("span must not exceed string length");
  if (len < 0)
    throw new Error("span must not be negative");

  const subNumbers = [];
  for (let i = 0; i + len <= series.length; i++)
    subNumbers.push(series.substring(i, i + len));

  return Math.max(...subNumbers.map(calculateDigits));
};

const calculateDigits = (subNumber) => {
  return subNumber.split("").reduce((a, b) => a * Number(b), 1);
};
