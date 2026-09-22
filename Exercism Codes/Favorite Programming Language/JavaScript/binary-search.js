export const find = (arr, el) => {
  let left = 0;
  let right = arr.length - 1;

  while (left <= right) {
    const mid = left + Math.floor((right - left) / 2);

    if (arr[mid] === el) {
      return mid;
    }

    if (arr[mid] > el) {
      right = mid - 1;
    }

    if (arr[mid] < el) {
      left = mid + 1;
    }
  }

  throw new Error('Value not in array');
};
