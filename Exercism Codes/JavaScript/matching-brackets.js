export const isPaired = (text) => {
  const stack = [];

  for (const x of text) {
    if ("([{".includes(x)) {
      stack.push(x);
    }
    if (x === ")") {
      if (stack.length > 0 && stack[stack.length - 1] === "(") {
        stack.pop();
      }
      else return false;
    }
    if (x === "]") {
      if (stack.length > 0 && stack[stack.length - 1] === "[") {
        stack.pop();
      }
      else return false;
    }
    if (x === "}") {
      if (stack.length > 0 && stack[stack.length - 1] === "{") {
        stack.pop();
      }
      else return false;
    }
  }
  
  return stack.length === 0;
};