export function getListOfWagons(...ids) {
  return ids;
}

export function fixListOfWagons(ids) {
  const [a, b, ...c] = ids;
  return [...c, a, b];
}

export function correctListOfWagons(ids, missingWagons) {
  const [a, ...b] = ids;
  return [a, ...missingWagons, ...b];
}

export function extendRouteInformation(information, additional) {
  return { ...information, ...additional };
}

export function separateTimeOfArrival(information) {
  const { timeOfArrival, ...info } = information;
  return [timeOfArrival, info];
}
