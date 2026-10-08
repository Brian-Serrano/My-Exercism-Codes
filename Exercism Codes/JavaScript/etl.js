//
// This is only a SKELETON file for the 'ETL' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const transform = (old) => {
  const result = {};
  for (const [key, value] of Object.entries(old)) {
    for (const i of value) {
      result[i.toLowerCase()] = Number(key);
    }
  }
  return result;
};
