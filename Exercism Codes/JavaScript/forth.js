//
// This is only a SKELETON file for the 'Forth' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Forth {
  constructor() {
    this.tokens = [];
    this.func = [];
    this.funcName = [];
    this.eval = false;
  }

  evaluate(instruction) {
    const f = instruction.toLowerCase().match(/[^ :;]+/g);
    if (/(^:.*;$)/.test(instruction)) {
      if (this.isInteger(f[0])) {
        throw new Error("Invalid definition");
      }
      this.funcName.unshift(f[0]);
      this.func.unshift(f.slice(1));
    }
    else {
      this.tokens = this.tokens.concat(f);
      this.eval = true;
    }
    if (this.eval) {
      this.st = this.call([], this.tokens, 0);
    }
  }

  get stack() {
    return this.st;
  }

  call(stack, tokens, where) {
    for (const token of tokens) {
      const index = this.funcName.indexOf(token);
      if (index != -1) {
        this.call(stack, this.func.slice(where)[index], index + 1);
        continue;
      }
      if (this.isInteger(token)) {
        stack.push(Number(token));
        continue;
      }
      if (token == "+") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        if (stack.length < 2) {
          throw new Error("Only one value on the stack");
        }
        stack.push(stack.pop() + stack.pop());
        continue;
      }
      if (token == "-") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        if (stack.length < 2) {
          throw new Error("Only one value on the stack");
        }
        const first = stack.pop();
        const second = stack.pop();
        stack.push(second - first);
        continue;
      }
      if (token == "*") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        if (stack.length < 2) {
          throw new Error("Only one value on the stack");
        }
        stack.push(stack.pop() * stack.pop());
        continue;
      }
      if (token == "/") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        if (stack.length < 2) {
          throw new Error("Only one value on the stack");
        }
        const first = stack.pop();
        if (first == 0) {
          throw new Error("Division by zero");
        }
        const second = stack.pop();
        stack.push(Math.floor(second / first));
        continue;
      }
      if (token == "dup") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        stack.push(stack[stack.length - 1]);
        continue;
      }
      if (token == "drop") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        stack.pop();
        continue;
      }
      if (token == "swap") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        if (stack.length < 2) {
          throw new Error("Only one value on the stack");
        }
        const first = stack.pop();
        const second = stack.pop();
        stack.push(first);
        stack.push(second);
        continue;
      }
      if (token == "over") {
        if (stack.length < 1) {
          throw new Error("Stack empty");
        }
        if (stack.length < 2) {
          throw new Error("Only one value on the stack");
        }
        stack.push(stack[stack.length - 2]);
        continue;
      }
      throw new Error("Unknown command");
    }
    return stack;
  }

  isInteger(str) {
    return /^-?\d+$/.test(str);
  }
}
