//
// This is only a SKELETON file for the 'Clock' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Clock {
  constructor(hour=0, minute=0) {
    this.time = new Date();
    this.time.setHours(0, 0, 0, 0);
    this.time.setHours(this.time.getHours() + hour);
    this.time.setMinutes(this.time.getMinutes() + minute);
  }

  toString() {
    return `${this.time.getHours().toString().padStart(2, '0')}:${this.time.getMinutes().toString().padStart(2, '0')}`;
  }

  plus(minute) {
    this.time.setMinutes(this.time.getMinutes() + minute);
    return this;
  }

  minus(minute) {
    this.time.setMinutes(this.time.getMinutes() - minute);
    return this;
  }

  equals(other) {
    return this.time.getHours() == other.time.getHours() && this.time.getMinutes() == other.time.getMinutes();
  }
}
