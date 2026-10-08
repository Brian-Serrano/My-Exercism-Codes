//
// This is only a SKELETON file for the 'Error handling' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const processString = (input) => {
  try {
    if (typeof input !== "string")
      throw new TypeError("input is not string");
    if (input.length == 0)
      return null;
    if (input.length > 100 || input.length < 10)
      throw new RangeError("input is not in the range");
    if (/\d/.test(input))
      throw new SyntaxError("input contains number");
    
    return input.toUpperCase();
  }
  catch (e) {
    console.log(e.message);
    throw e;
  }
};
