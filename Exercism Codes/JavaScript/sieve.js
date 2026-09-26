//
// This is only a SKELETON file for the 'Sieve' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const primes = (limit) => {
  const prime = new Array(limit + 1).fill(true);
  const result = [];

  for (let p = 2; p * p <= limit; p++) {
    if (prime[p]) {
      for (let i = p * p; i <= limit; i += p) {
        prime[i] = false;
      }
    }
  }
  for (let i = 2; i <= limit; i++) {
    if (prime[i]) {
      result.push(i);
    }
  }
  return result;
};
