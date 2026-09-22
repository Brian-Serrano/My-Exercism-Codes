export const rows = (amount) => {
  let prevArr = [1];
  let resArr = [];
  for(let i = 0; i < amount; i++){
    let arr = new Array(i + 1);
    for(let j = 0; j < arr.length; j++){
      arr[j] = isUndefined(prevArr[j]) + isUndefined(prevArr[j - 1]);
    }
    resArr.push(arr);
    prevArr = arr;
  }
  return resArr;
};

const isUndefined = (elem) => {
  return elem === undefined ? 0 : elem;
}