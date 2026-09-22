//
// This is only a SKELETON file for the 'All Your Base' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const convert = (digits, inputBase, outputBase) => {
  if (inputBase < 2) {
    throw new Error('Wrong input base');
  }
  if (outputBase < 2) {
    throw new Error('Wrong output base');
  }
  if (digits.length === 0 || (digits[0] === 0 && digits.length > 1)) {
    throw new Error('Input has wrong format');
  }

  const result = [];
  let dec = 0;
  const n = digits.length;

  for (let i = 0; i < n; i++) {
    if (digits[i] < 0 || digits[i] >= inputBase) {
      throw new Error('Input has wrong format');
    }

    dec += (inputBase ** (n - i - 1)) * digits[i];
  }

  while (dec > 0) {
    result.unshift(dec % outputBase);
    dec = Math.floor(dec / outputBase);
  }

  return result.length > 0 ? result : [0];
};
