export class SplitSecondStopwatch {
  constructor() {
    this.s = "ready";
    this.t = new Date(0);
    this.cl = new Date(0);
    this.pl = [];
  }

  get state() {
    return this.s;
  }

  get currentLap() {
    return this.toString(this.cl);
  }

  get total() {
    return this.toString(this.t);
  }

  get previousLaps() {
    return this.pl.map(l => this.toString(l));
  }

  start() {
    if (this.s == "running")
      throw new Error("cannot start an already running stopwatch");

    this.s = "running";
  }

  stop() {
    if (this.s != "running")
      throw new Error("cannot stop a stopwatch that is not running");

    this.s = "stopped";
  }

  lap() {
    if (this.s != "running")
      throw new Error("cannot lap a stopwatch that is not running");

    this.pl.push(this.cl);
    this.cl = new Date(0);
  }

  reset() {
    if (this.s != "stopped")
      throw new Error("cannot reset a stopwatch that is not stopped");

    this.s = "ready";
    this.t = new Date(0);
    this.cl = new Date(0);
    this.pl = [];
  }

  advanceTime(duration) {
    if (this.s == "running") {
      const [hours, minutes, seconds] = duration.split(":").map(Number);
      const timestamp = (hours * 3600 + minutes * 60 + seconds) * 1000;

      this.t.setTime(this.t.getTime() + timestamp);
      this.cl.setTime(this.cl.getTime() + timestamp);
    }
  }

  toString(date) {
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();

    const formatted = [hours, minutes, seconds]
      .map(num => String(num).padStart(2, '0'))
      .join(':');

    return formatted;
  }
}
