//
// This is only a SKELETON file for the 'Nth Prime' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const prime = (number) => {
  if (number <= 0) {
    throw new Error('there is no zeroth prime');
  }
  if (number === 1) {
    return 2;
  }
  let i = 1, count = 1;
  while (count != number) {
    i += 2;
    if (isPrime(i)) {
      count++;
    }
  }
  return i;
};

const isPrime = (num) => {
  for (let x = 3; x <= num ** 0.5; x += 2) {
    if (num % x === 0) {
      return false;
    }
  }
  return true;
}
