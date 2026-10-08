export function randomShipRegistryNumber() {
  return "NCC-" + Math.floor(1000 + Math.random() * 9000);
}

export function randomStardate() {
  return Math.random() * 1000 + 41000;
}

export function randomPlanetClass() {
  return ["D", "H", "J", "K", "L", "M", "N", "R", "T", "Y"][Math.floor(Math.random() * 10)];
}
