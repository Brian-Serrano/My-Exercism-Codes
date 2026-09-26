//
// This is only a SKELETON file for the 'Proverb' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const proverb = (...args) => {
  let qualifier = null;

  if (args.length > 0 && typeof args[args.length - 1] === "object") {
    qualifier = args.pop().qualifier;
  }

  const words = args;

  let sentence = "";
  for (let i = 0; i < words.length; i++) {
    if (i < words.length - 1) {
      sentence += "For want of a " + words[i] + " the " + words[i + 1] + " was lost.\n";
    }
    else {
      sentence += "And all for the want of a " + (qualifier !== null ? `${qualifier} ` : "") + words[0] + ".";
    }
  }
  return sentence;
};
