export function getCardPosition(stack, card) {
  return stack.indexOf(card);
}

export function doesStackIncludeCard(stack, card) {
  return stack.includes(card);
}

export function isEachCardEven(stack) {
  return stack.every(value => value % 2 == 0);
}

export function doesStackIncludeOddCard(stack) {
  return stack.some(value => value % 2 == 1);
}

export function getFirstOddCard(stack) {
  return stack.find(value => value % 2 == 1);
}

export function getFirstEvenCardPosition(stack) {
  return stack.findIndex(value => value % 2 == 0);
}
