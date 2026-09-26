//
// This is only a SKELETON file for the 'Circular Buffer' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

class CircularBuffer {
  constructor(capacity) {
    this.capacity = capacity;
    this.elements = [];
  }

  write(data) {
    if (this.elements.length == this.capacity) {
      throw new BufferFullError();
    }
    this.elements.push(data);
  }

  read() {
    if (this.elements.length == 0) {
      throw new BufferEmptyError();
    }
    return this.elements.shift();
  }

  forceWrite(data) {
    if (this.elements.length == this.capacity) {
      this.clear();
    }
    this.elements.push(data);
  }

  clear() {
    if (this.elements.length > 0) {
      this.elements.shift();
    }
  }
}

export default CircularBuffer;

export class BufferFullError extends Error {
  constructor() {
    super('Remove this line and implement the function');
  }
}

export class BufferEmptyError extends Error {
  constructor() {
    super('Remove this line and implement the function');
  }
}
