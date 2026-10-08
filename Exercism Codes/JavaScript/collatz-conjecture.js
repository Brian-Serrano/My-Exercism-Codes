//
// This is only a SKELETON file for the 'Collatz Conjecture' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const steps = (number) => {
  if (number <= 0)
    throw new Error("Only positive integers are allowed");

  let count = 0;
  while (number != 1) {
    number = number % 2 == 0 ? number / 2 : (number * 3) + 1;
    count++;
  }
  return count;
};
