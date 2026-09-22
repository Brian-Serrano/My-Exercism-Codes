export const primeFactors = (number) => {
  const result = [];
  let x = 2;
  while (number > 1) {
    if (number % x == 0) {
      result.push(x);
      number /= x;
    }
    else {
      x++;
    }
  }
  return result;
};
