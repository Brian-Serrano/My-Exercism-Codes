//
// This is only a SKELETON file for the 'Wordy' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const answer = (ques) => {
  const question = ques.substring(0, ques.length - 1).split(" ").slice(2).filter(x => x !== "by");
  const notValid = question.some(x => !(!isNaN(Number(x)) || ["plus", "minus", "multiplied", "divided"].includes(x)));
  if (notValid)
    throw new Error("Unknown operation");

  try {
    if (question.length % 2 == 0)
      throw new Error("Syntax error");

    let count = Number(question[0]);
    for (let x = 2; x < question.length; x += 2) {
      switch (question[x - 1]) {
        case "plus":
          count += Number(question[x]);
          break;
        case "minus":
          count -= Number(question[x]);
          break;
        case "multiplied":
          count *= Number(question[x]);
          break;
        case "divided":
          count /= Number(question[x]);
          break;
        default:
          throw new Error("Syntax error");
      }
    }
    return count;
  }
  catch (e) {
    throw new Error("Syntax error");
  }
};
