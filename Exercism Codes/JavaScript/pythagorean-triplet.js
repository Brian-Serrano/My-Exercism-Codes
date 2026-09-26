//
// This is only a SKELETON file for the 'Pythagorean Triplet' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export function triplets({ minFactor, maxFactor, sum }) {
  const result = [];
  const minF = minFactor ?? 0;
  const maxF = maxFactor ?? sum;

  const x = Math.min(maxF, Math.floor(sum / 3) + 1);
  for (let a = Math.max(1, minF); a < x; a++) {
    const y = Math.min(maxF, Math.floor((sum - a) / 2) + 1);
    for (let b = Math.max(a, minF); b < y; b++) {
      const c = sum - a - b;

      if (a * a + b * b == c * c && c >= minF && c <= maxF) {
        result.push(new Triplet(a, b, c));
      }
    }
  }
  return result;
}

class Triplet {
  constructor(a, b, c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  toArray() {
    return [this.a, this.b, this.c];
  }
}
