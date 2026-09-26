//
// This is only a SKELETON file for the 'Dominoes' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const chain = (dominoes) => {
  const input = [...dominoes];
  const output = [];
  let x = 0;
  let y = 0;

  if (input.length > 0) {
    output.push(input.shift());
  }
  while (true) {
    if (input.length == 0) {
      if (output.length == 0) {
        break;
      }
      if (output[0][0] != output[output.length - 1][1]) {
        return null;
      }
      break;
    }
    if (output[0][0] == input[0][0]) {
      output.unshift([input[0][1], input[0][0]]);
      input.shift();
      x = 0;
      y = 0;
      continue;
    }
    if (output[0][0] == input[0][1]) {
      output.unshift(input[0]);
      input.shift();
      x = 0;
      y = 0;
      continue;
    }
    if (output[output.length - 1][1] == input[0][0]) {
      output.push(input[0]);
      input.shift();
      x = 0;
      y = 0;
      continue;
    }
    if (output[output.length - 1][1] == input[0][1]) {
      output.push([input[0][1], input[0][0]]);
      input.shift();
      x = 0;
      y = 0;
      continue;
    }
    input.push(input.shift())
    if (++x >= input.length) {
      if (output[0][0] != output[output.length - 1][1]) {
        return null;
      }
      else {
        output.push(output.shift());
        x = 0;
        if (++y >= output.length) {
          return null;
        }
      }
    }
  }
  return output;
};
