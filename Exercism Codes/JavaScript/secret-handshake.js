//
// This is only a SKELETON file for the 'Secret Handshake' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const commands = (num) => {
  const binaryStr = num.toString(2).padStart(5, "0").split("").toReversed();
  const lst = ["wink", "double blink", "close your eyes", "jump"];
  const result = [];
  for (let x = 0; x < 4; x++) {
    if (binaryStr[x] == "1") {
      result.push(lst[x]);
    }
  }

  if (binaryStr[binaryStr.length - 1] == "1")
    result.reverse();

  return result;
};
