//
// This is only a SKELETON file for the 'Crypto Square' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Crypto {
  constructor(plaintext) {
    let normalized = plaintext.toLowerCase().replace(/\W/g, "");
    const ln = normalized.length;
    let c = Math.ceil(Math.sqrt(ln));
    let r = Math.floor(Math.sqrt(ln));
    if (c * r > ln) {
      normalized += " ".repeat(r - (ln % r));
    }
    if (c * r < ln) {
      normalized += " ".repeat(c - (ln % c));
      r++;
    }
    const rect = normalized.match(new RegExp(`.{${c}}`, "g"));
    const result = [];
    for (let idx = 0; idx < rect[0].length; idx++) {
      let str = "";
      for (const lst of rect) {
        str += lst[idx];
      }
      result.push(str);
    }
    this.cipher = result.join(" ");
  }

  get ciphertext() {
    return this.cipher;
  }
}
