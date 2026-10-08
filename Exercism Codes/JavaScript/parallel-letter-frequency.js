//
// This is only a SKELETON file for the 'Parallel Letter Frequency' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const parallelLetterFrequency = async (texts) => {
  const input = texts.join("").toLowerCase().replace(/[!@#$%^&*()_\-+={}[\]|\\:;"'<>,.?/\d\s]/g, "").split("");
    const counts = {};
    for (const i of input) {
      if (!(i in counts)) {
        counts[i] = 0;
      }
      counts[i]++;
    }
    return counts;
};
