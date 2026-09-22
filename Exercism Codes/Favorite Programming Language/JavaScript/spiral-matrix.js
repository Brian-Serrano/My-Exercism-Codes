export const spiralMatrix = (size) => {
    if(size == 0) return new Array();
    let matrix = new Array(size).fill(0).map(() => new Array(size).fill(0));
    let funcArr = [right, bottom, left, top];
    let funcIndex = 0;
    matrix[0][0] = 1;
    let index = [0, 0];
    for(let i = 2; i <= size * size; i++){
      let temp = funcArr[funcIndex](...index);
      if(!(temp[0] >= size || temp[0] < 0 || temp[1] >= size || temp[1] < 0)){
        if(matrix[temp[0]][temp[1]] == 0){
          matrix[temp[0]][temp[1]] = i;
          index = temp;
        }
        else {
          funcIndex = funcIndex >= funcArr.length - 1 ? 0 : funcIndex + 1;
          i--;
        }
      }
      else {
        funcIndex = funcIndex >= funcArr.length - 1 ? 0 : funcIndex + 1;
        i--;
      }
    }
    return matrix;
  };
  
  const right = (row, col) => [row, col + 1];
  const bottom = (row, col) => [row + 1, col];
  const left = (row, col) => [row, col - 1];
  const top = (row, col) => [row - 1, col];