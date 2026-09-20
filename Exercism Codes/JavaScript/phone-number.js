//
// This is only a SKELETON file for the 'Phone Number' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const clean = (number) => {
  let n = number.replace(/[\s\-()\.+]/g, "");
  if (n.length == 11) {
    if (n[0] != "1") {
      throw new Error("11 digits must start with 1");
    }
    else {
      n = n.substring(1);
    }
  }
  else if (n.length > 11) {
    throw new Error("Must not be greater than 11 digits");
  }
  else if (n.length < 10) {
    throw new Error("Must not be fewer than 10 digits");
  }

  if ([...n].some(c => /\p{P}/u.test(c))) {
    throw new Error("Punctuations not permitted");
  }
  if ([...n].some(c => /\p{L}/u.test(c))) {
    throw new Error("Letters not permitted");
  }
  if (n[0] == "0") {
    throw new Error("Area code cannot start with zero");
  }
  if (n[0] == "1") {
    throw new Error("Area code cannot start with one");
  }
  if (n[3] == "0") {
    throw new Error("Exchange code cannot start with zero");
  }
  if (n[3] == "1") {
    throw new Error("Exchange code cannot start with one");
  }
  return n;
};
