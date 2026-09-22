export const saddlePoints = (matrix) => {
  if(matrix.length == 0) return [];
  const max = matrix.map(x => Math.max(...x));
  const min = [...Array(matrix[0].length).keys()]
    .map(x => Math.min(...matrix.map(y => y[x])));
  return [...Array(matrix.length).keys()]
    .flatMap(x => [...Array(matrix[x].length).keys()]
    .filter(y => matrix[x][y] >= max[x] && matrix[x][y] <= min[y])
    .map(y => ({ row: x + 1, column: y + 1 })));
};
