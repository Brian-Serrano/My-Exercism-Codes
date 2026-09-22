export const rows = (letter) => {
  const result = [];

  for (let x = 65; x <= letter.charCodeAt(0); x++) {
    result.push(line(letter, x));
  }

  for (let x = letter.charCodeAt(0) - 1; x >= 65; x--) {
    result.push(line(letter, x));
  }

  return result;
};

const line = (letter, x) => {
  const pad1 = " ".repeat((letter.charCodeAt(0) - 65) - (x - 65));
  const pad2 = " ".repeat(x - 65);
  const firstHalfLine = pad1 + String.fromCharCode(x) + pad2;
  const secondHalfLine = pad2 + String.fromCharCode(x) + pad1;
  return firstHalfLine + secondHalfLine.substring(1);
}