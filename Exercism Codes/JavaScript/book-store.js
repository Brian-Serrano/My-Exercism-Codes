//
// This is only a SKELETON file for the 'BookStore' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const cost = (books) => {
  const cnt = getFrequency(books);
  const freq = Object.values(cnt).length > 0 ? Math.max(...Object.values(cnt)) : 0;
  const combination = findCombinations(cnt, freq, Object.keys(cnt), 0, [Array(freq).fill(0)]);
  const discounts = { 1: 0.0, 2: 0.4, 3: 0.8, 4: 1.6, 5: 2.0 };
  return Math.min(...combination.map(lst => lst.map(b => 100 * b * (8.0 - discounts[b])).reduce((a, b) => a + b, 0)));
};

const getFrequency = (books) => {
  const frequency = {};

  for (const x of books) {
    if (x in frequency) {
      frequency[x]++;
    }
    else {
      frequency[x] = 1;
    }
  }
  return frequency;
};

const findCombinations = (cnt, freq, nums, num, arr) => {
  if (num == nums.length) {
    return arr;
  }
  else {
    const arr2 = [...arr];
    arr = [];

    for (const x of arr2) {
      for (const positions of combinations([...Array(freq).keys()], cnt[nums[num]])) {
        const newArr = [...x];
        for (const j of positions) {
          newArr[j] += 1;
        }
        arr.push(newArr);
      }
    }
    return findCombinations(cnt, freq, nums, num + 1, arr);
  }
};

const combinations = (arr, r) => {
  const results = [];

  const backtrack = (start, path) => {
    if (path.length === r) {
      results.push([...path]);
      return;
    }

    for (let i = start; i < arr.length; i++) {
      path.push(arr[i]);
      backtrack(i + 1, path);
      path.pop();
    }
  }

  backtrack(0, []);
  return results;
};