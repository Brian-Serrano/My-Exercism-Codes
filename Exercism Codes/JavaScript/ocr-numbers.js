//
// This is only a SKELETON file for the 'OCR Numbers' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const convert = (input) => {
  const text = input.split("\n");

  if (text.length % 4 != 0)
    throw new Error();
  if (text[0].length % 3 != 0)
    throw new Error();

  const readers = [
    t => arrayEquals(t, [" _ ", "| |", "|_|", "   "]),
    t => arrayEquals(t, ["   ", "  |", "  |", "   "]),
    t => arrayEquals(t, [" _ ", " _|", "|_ ", "   "]),
    t => arrayEquals(t, [" _ ", " _|", " _|", "   "]),
    t => arrayEquals(t, ["   ", "|_|", "  |", "   "]),
    t => arrayEquals(t, [" _ ", "|_ ", " _|", "   "]),
    t => arrayEquals(t, [" _ ", "|_ ", "|_|", "   "]),
    t => arrayEquals(t, [" _ ", "  |", "  |", "   "]),
    t => arrayEquals(t, [" _ ", "|_|", "|_|", "   "]),
    t => arrayEquals(t, [" _ ", "|_|", " _|", "   "])
  ];

  const cropped = crop(text);
  let s = "";

  for (let j = 0; j < cropped.length; j++) {
    for (let k = 0; k < cropped[j].length; k++) {
      for (let i = 0; i <= readers.length; i++) {
        if (i == readers.length)
          s += "?";
        else if (readers[i](cropped[j][k])) {
          s += i;
          break;
        }
      }
    }
    if (j != cropped.length - 1)
      s += ",";
  }
  return s;
};

const crop = (text) => {
  const result = [];

  for (let i = 0; i < text.length; i += 4)
    result.push(text.slice(i, i + 4));

  return result.map(r => zip(r.map(s => s.match(/.{3}/g))));
};

const zip = (input) => {
  return Array.from({ length: input[0].length }, (value, index) => index)
    .map(t => input.map(l => l[t]));
};

const arrayEquals = (a, b) => 
  Array.isArray(a) && 
  Array.isArray(b) && 
  a.length === b.length && 
  a.every((val, index) => val === b[index]);
