const lst = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"];

export const decodedValue = (colors) => {
  return Number(`${lst.indexOf(colors[0])}${lst.indexOf(colors[1])}`);
};
