export class List {
  constructor(values = []) {
    this.values = values;
  }

  append(other) {
    return new List(this.values.concat(other.values));
  }

  concat(other) {
    let newValues = this.values;
    for (const x of other.values) {
      newValues = newValues.concat(x.values);
    }
    return new List(newValues);
  }

  filter(func) {
    return new List(this.values.filter(func));
  }

  map(func) {
    return new List(this.values.map(func));
  }

  length() {
    return this.values.length;
  }

  foldl(func, initial) {
    for (let i = 0; i < this.length(); i++) {
      initial = func(initial, this.values[i]);
    }
    return initial;
  }

  foldr(func, initial) {
    for (let i = this.length() - 1; i >= 0; i--) {
      initial = func(initial, this.values[i]);
    }
    return initial;
  }

  reverse() {
    return new List(this.values.reverse());
  }
}