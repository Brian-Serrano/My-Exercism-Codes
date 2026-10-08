//
// This is only a SKELETON file for the 'Variable Length Quantity' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const encode = (numbers) => {
  let result = [];
  for (let i = 0; i < numbers.length; i++) {
    if (numbers[i] == 0) {
      result.push(0);
      continue;
    }

    const res = [];

    while (numbers[i] > 0) {
      res.push(numbers[i] & 0x7F);
      numbers[i] >>>= 7;
    }
    for (let j = 1; j < res.length; j++) {
      res[j] |= 0x80;
    }
    res.reverse();
    result = [...result, ...res];
  }
  return result;
};

export const decode = (bytes) => {
  if ((bytes[bytes.length - 1] & 0x80) != 0)
    throw new Error("Incomplete sequence");

  const result = [];
  let c = 0;
  result.push(0n);
  for (let i = 0; i < bytes.length; i++) {
    result[c] = (result[c] << 7n) | BigInt(bytes[i] & 0x7F);
    if ((bytes[i] & 0x80) == 0 && bytes.length - 1 != i) {
      result.push(0n);
      c++;
    }
  }
  return result.map(x => Number(x));
};
