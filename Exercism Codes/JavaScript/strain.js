//
// This is only a SKELETON file for the 'Strain' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const keep = (collection, predicate) => {
  const result = [];
  for (const c of collection)
    if (predicate(c))
      result.push(c);
  return result;
};

export const discard = (collection, predicate) => {
  const result = [];
  for (const c of collection)
    if (!predicate(c))
      result.push(c);
  return result;
};
