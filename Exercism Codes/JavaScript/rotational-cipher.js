export const rotate = (text, key) => {
  return text.split("").map(x => rotateLetter(x, key)).join("");
};

const rotateLetter = (x, key) => {
  return /[A-Za-z]/.test(x) ? String.fromCharCode((((x.charCodeAt(0) - isUpper(x)) + key) % 26) + isUpper(x)) : x;
}

const isUpper = (x) => {
  return /[A-Z]/.test(x) ? 65 : 97;
};