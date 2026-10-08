//
// This is only a SKELETON file for the 'Flatten Array' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const flatten = (source) => {
  let result = [];
  for (const element of source) {
    if (Array.isArray(element)) {
      result = [...result, ...flatten(element)];
      continue;
    }
    if (typeof element == "number") {
      result.push(element);
    }
  }
  return result;
};
