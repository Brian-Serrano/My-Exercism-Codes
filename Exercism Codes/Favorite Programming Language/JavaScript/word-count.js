//
// This is only a SKELETON file for the 'Word Count' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const countWords = (sentence) => {
  const arr = sentence.toLowerCase().trim().split(/[^a-zA-Z0-9']+/);
  const result = {};
  for (const word of arr) {
    const w = word.replace(/^'{1,2}|'{1,2}$/g, "");
    if (w.length > 0) {
      if (w in result) {
        result[w] += 1;
      }
      else {
        result[w] = 1;
      }
    }
  }
  return result;
};
