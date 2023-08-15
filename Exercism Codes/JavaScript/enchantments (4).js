export function cardTypeCheck(stack, card) {
  return stack.filter(s => s == card).length;
}

export function determineOddEvenCards(stack, type) {
  return stack.filter(s => s % 2 == (type ? 0 : 1)).length;
}
