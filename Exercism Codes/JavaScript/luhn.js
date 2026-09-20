//
// This is only a SKELETON file for the 'Luhn' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const valid = (number) => {
  const cardNum = number.replaceAll(" ", "");
  if (!(/^\d+$/.test(cardNum)) || cardNum.length < 2) {
    return false;
  }
  const digits = cardNum.split("").map(c => Number(c));
  for (let i = digits.length % 2; i < digits.length; i += 2) {
    digits[i] *= 2;
    if (digits[i] > 9) {
      digits[i] -= 9;
    }
  }
  return digits.reduce((a, b) => a + b, 0) % 10 == 0;
};
