//
// This is only a SKELETON file for the 'Rational Numbers' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Rational {
  constructor(num, den) {
    const g = this.gcf(num, den);
    this.num = Math.floor(num / g);
    this.den = Math.floor(den / g);
  }

  add(other) {
    const l = this.lcm(this.den, other.den);
    return this.rdc(this.num * (l / this.den) + other.num * (l / other.den), l);
  }

  sub(other) {
    const l = this.lcm(this.den, other.den);
    return this.rdc(this.num * (l / this.den) - other.num * (l / other.den), l);
  }

  mul(other) {
    return this.rdc(this.num * other.num, this.den * other.den);
  }

  div(other) {
    return this.rdc(this.num * other.den, other.num * this.den);
  }

  abs() {
    return this.rdc(Math.abs(this.num), Math.abs(this.den));
  }

  exprational(power) {
    const a = Math.pow(this.num, Math.abs(power));
    const b = Math.pow(this.den, Math.abs(power));
    return power >= 0 ? this.rdc(a, b) : this.rdc(b, a);
  }

  expreal(base) {
    const result = Math.pow(base, this.num / this.den);

    if (Math.abs(result - Math.round(result)) < 1e-10) {
      return Math.round(result);
    }
    
    return result;
  }

  reduce() {
    return this.rdc(this.num, this.den);
  }

  rdc(num, den) {
    const g = this.gcf(num, den);
    return new Rational(Math.floor(num / g), Math.floor(den / g));
  }

  gcf(a, b) {
    while (b != 0) {
      let temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  lcm(a, b) {
    return Math.floor((a * b) / this.gcf(a, b));
  }
}
