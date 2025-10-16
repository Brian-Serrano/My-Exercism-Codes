const ones = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
const tens = ["ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
const teens = ["eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];

export const say = (n) => {
  if (n === 0) {
    return "zero";
  }
  if (n < 0 || n > 999999999999) {
    throw new Error('Number must be between 0 and 999,999,999,999.');
  }

  const result = [];
  const str = String(n);

  for (let x = str.length - 1; x >= 0; x--) {
    const idx = str.length - x - 1;
    if (idx / 3 === 1) {
      result.unshift("thousand");
    }
    if (idx / 3 === 2) {
      if (parseInt(str.substring(x + 1, x + 4)) === 0) {
        result.shift();
      }
      result.unshift("million");
    }
    if (idx / 3 === 3) {
      if (parseInt(str.substring(x + 1, x + 4)) === 0) {
        result.shift();
      }
      result.unshift("billion");
    }

    if (parseInt(str[x]) !== 0) {
      if (idx % 3 === 0) {
        result.unshift(ones[parseInt(str[x]) - 1]);
      }
      if (idx % 3 === 1) {
        if (parseInt(str[x]) === 1 && parseInt(str[x + 1]) !== 0) {
          result.shift();
          result.unshift(teens[parseInt(str[x + 1]) - 1]);
        }
        else {
          result.unshift(tens[parseInt(str[x]) - 1]);
        }
      }
      if (idx % 3 === 2) {
        result.unshift(ones[parseInt(str[x]) - 1], "hundred");
      }
    }
  }

  return join(result);
};

const join = (lst) => {
  let result = lst[0];

  for (let x = 1; x < lst.length; x++) {
    if (tens.includes(lst[x - 1]) && ones.includes(lst[x])) {
      result += "-" + lst[x];
    }
    else {
      result += " " + lst[x];
    }
  }

  return result;
};