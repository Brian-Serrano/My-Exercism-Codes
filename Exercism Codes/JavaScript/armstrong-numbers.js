//
// This is only a SKELETON file for the 'Armstrong Numbers' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const isArmstrongNumber = (number) => {
  return number == number.toString().split("").map(x => BigInt(x) ** BigInt(number.toString().length)).reduce((a, b) => a + b, 0n);
};
