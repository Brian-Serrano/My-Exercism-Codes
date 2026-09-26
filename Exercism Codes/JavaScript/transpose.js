//
// This is only a SKELETON file for the 'Transpose' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const transpose = (text) => {
  const st = padStringArray(text);
  const sb = Array.from({ length: st.length > 0 ? st[0].length : 0 }, (_) => "");
  for (const s of st) {
    for (let j = 0; j < s.length; j++) {
      sb[j] += s[j];
    }
  }
  return sb;
};

const padStringArray = (text) => {
  for (let i = text.length - 1; i > 0; i--) {
    if (text[i].length > text[i - 1].length) {
      const rep = text[i].length - text[i - 1].length;
      text[i - 1] += " ".repeat(rep);
    }
  }
  return text;
};