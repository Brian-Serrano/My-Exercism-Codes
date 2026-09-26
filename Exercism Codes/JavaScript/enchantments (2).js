export function getFirstCard(deck) {
    return deck[0];
  }
  
  export function getSecondCard(deck) {
    return deck[1];
  }
  
  export function swapTopTwoCards(deck) {
    const [a, b, ...c] = deck;
    return [b, a, ...c];
  }
  
  export function discardTopCard(deck) {
    const [a, ...b] = deck;
    return [a, [...b]];
  }
  
  const FACE_CARDS = ['jack', 'queen', 'king'];
  
  export function insertFaceCards(deck) {
    const [a, ...b] = deck;
    return [a, ...FACE_CARDS, ...b];
  }