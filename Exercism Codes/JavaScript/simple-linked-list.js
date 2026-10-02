//
// This is only a SKELETON file for the 'Simple Linked List' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Element {
  constructor(value) {
    this.val = value;
    this.nxt = null;
  }

  get value() {
    return this.val;
  }

  get next() {
    return this.nxt;
  }

  set(element) {
    this.nxt = element;
  }
}

export class List {
  constructor(values = []) {
    this.h = null;

    for (const value of values) {
      const node = this.h;
      this.h = new Element(value);
      this.h.set(node);
    }
  }

  add(nextValue) {
    const node = this.h;
    this.h = nextValue;
    this.h.set(node);
  }

  get length() {
    if (!this.h) {
      return 0;
    }
    else {
      let count = 1;
      let node = this.h;
      while (node.next) {
        count++;
        node = node.next;
      }
      return count;
    }
  }

  get head() {
    return this.h;
  }

  toArray() {
    const lst = [];
    let node = this.h;
    while (node) {
      lst.push(node.value);
      node = node.next;
    }
    return lst;
  }

  reverse() {
    return new List(this.toArray());
  }
}
