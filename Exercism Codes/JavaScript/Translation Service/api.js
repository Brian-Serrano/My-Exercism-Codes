import { AbusiveClientError, NotAvailable, Untranslatable } from './errors';

const mutex = { current: false };

export class ExternalApi {
  constructor(values = {}) {
    this.values = JSON.parse(JSON.stringify(values));
  }

  register(value, translation, quality = undefined) {
    if (typeof this.values[value] === 'undefined') {
      this.values[value] = [];
    }

    this.values[value].push(translation ? { translation, quality } : null);
    return this;
  }

  fetch(text) {
    if (typeof text !== 'string') {
      throw new BadRequest(
        `Expected text when calling fetch(text), actual ${typeof text}.`,
      );
    }

    // Check if client is banned
    if (mutex.current) {
      return rejectWithRandomDelay(new AbusiveClientError());
    }

    if (this.values[text] && this.values[text][0]) {
      return resolveWithRandomDelay(this.values[text][0]);
    }

    if (this.values[text]) {
      return rejectWithRandomDelay(new NotAvailable(text));
    }

    return rejectWithRandomDelay(new Untranslatable());
  }

  request(text, callback) {
    if (typeof text !== 'string') {
      throw new BadRequest(
        `Expected string text when calling request(text, callback), actual ${typeof text}.`,
      );
    }

    if (typeof callback !== 'function') {
      throw new BadRequest(
        `Expected callback function when calling fetch(text, callback), actual ${typeof callback}.`,
      );
    }

    if (this.values[text] && this.values[text][0]) {
      mutex.current = true;
      callback(new AbusiveClientError());
      return;
    }

    if (this.values[text]) {
      this.values[text].shift();

      // If it's now available, yay, otherwise, nay
      setTimeout(
        () => callback(this.values[text][0] ? undefined : makeRandomError()),
        1,
      );
      return;
    }

    callback(new Untranslatable());
  }
}

function resolveWithRandomDelay(value) {
  const timeout = Math.random() * 100;
  return new Promise((resolve) => {
    setTimeout(() => resolve(value), timeout);
  });
}

function rejectWithRandomDelay(value) {
  const timeout = Math.random() * 100;
  return new Promise((_, reject) => {
    setTimeout(() => reject(value), timeout);
  });
}

function makeRandomError() {
  return new Error(`Error code ${Math.ceil(Math.random() * 10000)}`);
}

class BadRequest extends Error {
  constructor(message) {
    super(message);
  }
}
