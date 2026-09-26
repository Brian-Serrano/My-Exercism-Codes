//
// This is only a SKELETON file for the 'Run Length Encoding' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const encode = (string) => {
  let count = 1, output = "";

  for (let i = 1; i <= string.length; i++) {
    if (i < string.length && string[i] == string[i - 1]) {
      count++;
    }
    else {
      output += (count < 2 ? "" : count) + string[i - 1];
      count = 1;
    }
  }
  return output;
};

export const decode = (string) => {
  const matches = string.match(/(\d*[A-Z a-z])/g);

  if (matches === null) {
    return "";
  }
  
  let output = "";

  for (const match of matches) {
    const letter = match[match.length - 1];
    const number = match.substring(0, match.length - 1);
    output += letter.repeat(number.length === 0 ? 1 : Number(number));
  }

  return output;
};
