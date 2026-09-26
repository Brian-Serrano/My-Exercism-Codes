export const encode = (phrase, key) => {
  if (key.a % 2 == 0 || key.a % 13 == 0) {
    throw new Error("a and m must be coprime.");
  }
  return phrase.toLowerCase().replace(/\W/g, "").split("")
    .map(x => x.charCodeAt(0) >= 48 && x.charCodeAt(0) <= 57 ?
      x.charCodeAt(0) - "a".charCodeAt(0) :
      ((key.a * (x.charCodeAt(0) - "a".charCodeAt(0))) + key.b) % 26)
    .map(x => String.fromCharCode(x + "a".charCodeAt(0)))
    .join("").match(/.{1,5}/g).join(" ");
};

export const decode = (phrase, key) => {
  if (key.a % 2 == 0 || key.a % 13 == 0) {
    throw new Error("a and m must be coprime.");
  }
  return phrase.toLowerCase().replace(/\W/g, "").split("")
    .map(x => x.charCodeAt(0) >= 48 && x.charCodeAt(0) <= 57 ?
      x.charCodeAt(0) :
      (mmi(key.a) * ((x.charCodeAt(0) - "a".charCodeAt(0)) - key.b)) % 26)
    .map(x => String.fromCharCode(
      x >= 48 && x <= 57 ? x : ((x + 26) % 26) + "a".charCodeAt(0)))
    .join("");
};

const mmi = (a) => {
  for (let i = 1; i < 26; i++) {
    if (a * i % 26 == 1) {
      return i;
    }
  }
  return -1;
};