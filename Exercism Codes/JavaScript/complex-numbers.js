//
// This is only a SKELETON file for the 'Complex Numbers' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class ComplexNumber {
  constructor(real, imaginary) {
    this.rel = real;
    this.imaginary = imaginary;
  }

  get real() {
    return this.rel;
  }

  get imag() {
    return this.imaginary;
  }

  add(other) {
    return new ComplexNumber(this.rel + other.rel, this.imaginary + other.imaginary);
  }

  sub(other) {
    return new ComplexNumber(this.rel - other.rel, this.imaginary - other.imaginary);
  }

  div(other) {
    denominator = other.rel * other.rel + other.imaginary * other.imaginary;
    return new ComplexNumber(
      ((this.rel * other.rel) + (this.imaginary * other.imaginary)) / denominator,
      ((this.imaginary * other.rel) - (this.rel * other.imaginary)) / denominator
    );
  }

  mul(other) {
    return new ComplexNumber(
      (this.rel * other.rel) - (this.imaginary * other.imaginary),
      (this.rel * other.imaginary) + (this.imaginary * other.rel)
    );
  }

  get abs() {
    return Math.sqrt(this.rel * this.rel + this.imaginary * this.imaginary);
  }

  get conj() {
    return new ComplexNumber(this.rel, -this.imaginary === 0 ? 0 : -this.imaginary);
  }

  get exp() {
    return new ComplexNumber(Math.exp(this.rel) * Math.cos(this.imaginary), Math.exp(this.rel) * Math.sin(this.imaginary));
  }
}
