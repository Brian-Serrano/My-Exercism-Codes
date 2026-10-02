//
// This is only a SKELETON file for the 'Linked List' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class LinkedList {
  constructor() {
    this.elements = [];
  }
  
  push(item) {
    this.elements.push(item);
  }

  pop() {
    return this.elements.pop();
  }

  shift() {
    return this.elements.shift();
  }

  unshift(item) {
    this.elements.unshift(item);
  }

  delete(item) {
    if (this.elements.includes(item)) {
      this.elements.splice(this.elements.indexOf(item), 1);
    }
  }

  count() {
    return this.elements.length;
  }
}
