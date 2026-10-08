// This is only a SKELETON file for the 'Robot Name' exercise. It's been
// provided as a convenience to get your started writing code faster.

const usedNames = new Set();

export class Robot {
  #name;
  
  constructor() {
    this.reset();
  }

  get name() {
    return this.#name;
  }

  reset() {
    let name = generateName();
    while (usedNames.has(name))
      name = generateName();

    usedNames.add(name);
    this.#name = name;
  }
}

Robot.releaseNames = () => {
  usedNames.clear();
};

const generateRandomName = (start, end, length) => {
  const a = start.charCodeAt(0);
  const b = end.charCodeAt(0);
  const generateLetter = () => String.fromCharCode(Math.floor(Math.random() * (b - a + 1)) + a);
  return Array.from({ length }, generateLetter).join("");
};

const generateName = () => {
  return generateRandomName("A", "Z", 2) + generateRandomName("0", "9", 3);
};