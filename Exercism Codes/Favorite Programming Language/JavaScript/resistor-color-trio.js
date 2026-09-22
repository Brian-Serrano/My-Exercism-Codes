export class ResistorColorTrio {
  constructor(colors) {
    const COLORS = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"];

    if (colors.some(c => !COLORS.includes(c))) {
      throw new Error("invalid color");
    }

    const numStr = String(COLORS.indexOf(colors[0])) + COLORS.indexOf(colors[1]) + "0".repeat(COLORS.indexOf(colors[2]));
    let num = parseInt(numStr);
    let unit;
    if (num >= 1000000000) {
      num /= 1000000000;
      unit = "gigaohms";
    }
    else if (num >= 1000000) {
      num /= 1000000;
      unit = "megaohms";
    }
    else if (num >= 1000) {
      num /= 1000;
      unit = "kiloohms";
    }
    else {
      unit = "ohms";
    }
    this._label = `Resistor value: ${num} ${unit}`;
  }

  get label() {
    return this._label;
  }
}