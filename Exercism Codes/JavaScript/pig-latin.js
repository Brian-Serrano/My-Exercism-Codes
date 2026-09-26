export const translate = (text) => {
  const result = [];

  for (const x of text.split(" ")) {
    let sl = 0;
    while (true) {
      const isVowel = "aeiou".includes(x[sl]);
      const rule1 = x.substring(sl, sl + 2) === "yt" || x.substring(sl, sl + 2) === "xr";
      const rule4 = (!("aeiou".includes(x[sl - 1])) && x[sl - 1] !== undefined) && x[sl] === "y";

      if (isVowel || rule1 || rule4) {
        break;
      }

      if (x.substring(sl, sl + 2) === "qu") {
        sl += 1;
      }

      sl += 1;
    }
    result.push(x.substring(sl) + x.substring(0, sl) + "ay");
  }

  return result.join(" ");
};