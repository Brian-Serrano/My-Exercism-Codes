export const encode = (text) => {
  const result = [];
  for (const x of text) {
    if (/\w/.test(x)) {
      result.push(transform(x));

      if ((result.length + 1) % 6 == 0) {
        result.push(" ");
      }
    }
  }
  return result.join("").trim();
};

export const decode = (text) => {
  return text.split("").filter(x => /\w/.test(x)).map(transform).join("");
};

const transform = (x) => {
  return /[A-Za-z]/.test(x) ? String.fromCharCode((26 - (x.toLowerCase().charCodeAt(0) - 97) - 1) + 97) : x;
};