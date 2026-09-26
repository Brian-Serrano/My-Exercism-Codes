export class Cipher {
  constructor(key) {
    if (key === undefined) {
      this._key = new Array(100).fill(0).map(_ => String.fromCharCode(Math.floor(Math.random() * 26) + 97)).join("");
    }
    else {
      this._key = key;
    }
  }

  encode(text = "") {
    return text.split("").map((x, idx) => {
      const res = (x.charCodeAt(0) - 97) + (this._key.charCodeAt(idx % this._key.length) - 97);
      const result = 97 + (res % 26);
      return String.fromCharCode(result);
    }).join("");
  }

  decode(text) {
    return text.split("").map((x, idx) => {
      const res = (x.charCodeAt(0) - 97) - (this._key.charCodeAt(idx % this._key.length) - 97);
      const result = 97 + ((res % 26) + (res < 0 ? 26 : 0));
      return String.fromCharCode(result);
    }).join("");
  }

  get key() {
    return this._key;
  }
}