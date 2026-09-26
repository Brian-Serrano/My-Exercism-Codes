//
// This is only a SKELETON file for the 'Knapsack' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const knapsack = (maximumWeight, items) => {
  const dp = Array.from({ length: items.length + 1 }, _ => new Array(maximumWeight + 1).fill(0));

  for (let i = 1; i <= items.length; i++) {
    const item = items[i - 1];
    for (let j = 1; j <= maximumWeight; j++) {
      if (item.weight <= j) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.weight] + item.value);
      }
      else {
        dp[i][j] = dp[i - 1][j];
      }
    }
  }
  return dp[items.length][maximumWeight];
};
