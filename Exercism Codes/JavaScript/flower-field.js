//
// This is only a SKELETON file for the 'Flower Field' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const annotate = (input) => {
  const matrix = input.map(x => x.split(""));

  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < matrix[i].length; j++) {
      if (matrix[i][j] != "*") {
        let k = 0;
        if (i + 1 < matrix.length && matrix[i + 1][j] == "*") k++;
        if (i - 1 >= 0 && matrix[i - 1][j] == "*") k++;
        if (j + 1 < matrix[i].length && matrix[i][j + 1] == "*") k++;
        if (j - 1 >= 0 && matrix[i][j - 1] == "*") k++;
        if (i + 1 < matrix.length && j + 1 < matrix[i].length && matrix[i + 1][j + 1] == "*") k++;
        if (i + 1 < matrix.length && j - 1 >= 0 && matrix[i + 1][j - 1] == "*") k++;
        if (i - 1 >= 0 && j + 1 < matrix[i].length && matrix[i - 1][j + 1] == "*") k++;
        if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] == "*") k++;

        if (k > 0)
          matrix[i][j] = k.toString();
      }
    }
  }

  return matrix.map(x => x.join(""));
};
