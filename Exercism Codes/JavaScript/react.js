//
// This is only a SKELETON file for the 'React' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class InputCell {
  constructor(value) {
    this.value = value;
    this.observers = [];
  }

  setValue(value) {
    const values = [];

    for (const observer of this.observers) {
      values.push(observer[0].value);
    }

    this.value = value;

    for (let i = 0; i < this.observers.length; i++) {
      const observer = this.observers[i];
      if (observer[0].value != values[i]) {
        observer[1].values.push(observer[1].fn(observer[0]));
      }
    }
  }
}

export class ComputeCell {
  constructor(inputCells, fn) {
    this.inputCells = inputCells;
    this.fn = fn;
  }

  get value() {
    return this.fn(this.inputCells);
  }

  addCallback(cb) {
    this.add(cb, this.inputCells, new Set())
  }

  add(cb, cells, visited) {
    for (const cell of cells) {
      if (visited.has(cell)) {
        continue;
      }
      visited.add(cell);
      
      if (cell instanceof InputCell) {
        cell.observers.push([this, cb]);
      }
      else {
        this.add(cb, cell.inputCells, visited);
      }
    }
  }

  removeCallback(cb) {
    this.remove(cb, this.inputCells);
  }

  remove(cb, cells) {
    for (const cell of cells) {
      if (cell instanceof InputCell) {
        const callbacks = cell.observers.map(x => x[1]);
        if (callbacks.includes(cb)) {
          cell.observers.splice(callbacks.indexOf(cb), 1);
        }
      }
      else {
        this.remove(cb, cell.inputCells);
      }
    }
  }
}

export class CallbackCell {
  constructor(fn) {
    this.fn = fn;
    this.values = [];
  }
}
