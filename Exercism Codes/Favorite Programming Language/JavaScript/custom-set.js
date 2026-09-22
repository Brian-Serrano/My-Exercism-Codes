export class CustomSet {
  constructor(set = []) {
    this.set = new Set(set);
  }

  empty() {
    return this.set.size === 0;
  }

  contains(el) {
    return this.set.has(el);
  }

  add(el) {
    return new CustomSet(this.set.add(el));
  }

  subset(other) {
    return this.set.isSubsetOf(other.set);
  }

  disjoint(other) {
    return this.set.isDisjointFrom(other.set);
  }

  eql(other) {
    return this.set.size === other.set.size && [...this.set].every((x) => other.set.has(x));
  }

  union(other) {
    return new CustomSet(this.set.union(other.set));
  }

  intersection(other) {
    return new CustomSet(this.set.intersection(other.set));
  }

  difference(other) {
    return new CustomSet(this.set.difference(other.set));
  }
}
