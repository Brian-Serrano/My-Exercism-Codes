//
// This is only a SKELETON file for the 'Palindrome Products' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Palindromes {
  constructor(minFactor, maxFactor) {
    this.minFactor = minFactor;
    this.maxFactor = maxFactor;
  }
  
  static generate({ maxFactor, minFactor }) {
    return new Palindromes(minFactor, maxFactor);
  }

  get largest() {
    if (this.minFactor > this.maxFactor) {
      throw new Error("min must be <= max");
    }
    let maximum = null;
    let factors = [];
    for (let i = this.minFactor; i <= this.maxFactor; i++) {
      for (let j = i; j <= this.maxFactor; j++) {
        const product = i * j;

        if (maximum != null && product < maximum)
          continue;

        if (this.isPalindrome(product)) {
          if (maximum == null || product > maximum) {
            maximum = product;
            factors = [];
          }
          factors.push([i, j]);
        }
      }
    }
    return { value: maximum, factors: factors };
  }

  get smallest() {
    if (this.minFactor > this.maxFactor) {
      throw new Error("min must be <= max");
    }
    let minimum = null;
    let factors = [];
    for (let i = this.minFactor; i <= this.maxFactor; i++) {
      for (let j = i; j <= this.maxFactor; j++) {
        const product = i * j;

        if (minimum != null && product > minimum)
          continue;

        if (this.isPalindrome(product)) {
          if (minimum == null || product < minimum) {
            minimum = product;
            factors = [];
          }
          factors.push([i, j]);
        }
      }
    }
    return { value: minimum, factors: factors };
  }

  isPalindrome(number) {
    return number.toString() == number.toString().split("").reverse().join("");
  }
}
