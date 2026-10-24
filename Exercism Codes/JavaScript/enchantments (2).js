export function getFirstCard([deck]) {
  return deck;
}

export function getSecondCard([deck1, deck2]) {
  return deck2;
}

export function swapTwoCards([deck1, deck2]) {
  return [deck2, deck1];
}

export function shiftThreeCardsAround([one, two, three]) {
  return [two, three, one];
}

export function pickNamedPile({ chosen, disregarded }) {
  return chosen;
}

export function swapNamedPile({ chosen, disregarded }) {
  return { chosen: disregarded, disregarded: chosen };
}
