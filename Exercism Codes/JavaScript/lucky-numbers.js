export function twoSum(array1, array2) {
    return Number(array1.join('')) + Number(array2.join(''));
  }
  
  export function luckyNumber(value) {
    return String(value) == String(value).split('').reverse().join('');
  }
  
  export function errorMessage(input) {
    if(!Boolean(input)) return "Required field";
    else if(isNaN(Number(input)) || Number(input) == 0) return "Must be a number besides 0";
    else return "";
  }