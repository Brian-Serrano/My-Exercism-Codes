//
// This is only a SKELETON file for the 'Change' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Change {
  calculate(coinArray, target) {
    if (target == 0)
      return [];
    if (target < 0)
      throw new Error("Negative totals are not allowed.");

    let possibleCoins = coinArray.map(x => [x]);
    while (!possibleCoins.some(c => this.sum(c) == target)) {
      possibleCoins = [...new Set(possibleCoins.flatMap(c => 
        this.compute(c, coinArray)).map(c => this.toString(c)))]
        .map(c => this.toArray(c));

      if (possibleCoins.every(c => this.sum(c) > target))
        throw new Error(`The total ${target} cannot be represented in the given currency.`);
    }
    return possibleCoins.filter(c => this.sum(c) == target)[0];
  }

  compute(coin, coins) {
    const result = [];
    for (const c of coins) {
      const r = [...coin];
      r.push(c);
      result.push(r.toSorted((a, b) => a - b));
    }
    return result;
  }

  sum(c) {
    return c.reduce((a, b) => a + b, 0);
  }

  toString(array) {
    return array.join(",");
  }

  toArray(string) {
    return string.split(",").map(x => Number(x));
  }
}
