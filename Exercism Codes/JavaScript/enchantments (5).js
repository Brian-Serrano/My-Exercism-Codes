export function seeingDouble(deck) {
  return deck.map(d => d * 2);
}

export function threeOfEachThree(deck) {
  return deck.reduce((acc, next) => {
    if(next === 3) {
      acc.push(next);
      acc.push(next);
    }
    acc.push(next);
    return acc;
  }, []);
}

export function middleTwo(deck) {
  return deck.splice(4, 2);
}

export function sandwichTrick(deck) {
  deck.splice(deck.length / 2, 0, deck[deck.length - 1], deck[0]);
  deck.pop();
  deck.shift();
  return deck;
}

export function twoIsSpecial(deck) {
  return deck.filter(d => d == 2);
}

export function perfectlyOrdered(deck) {
  return deck.sort((x, y) => x - y);
}

export function reorder(deck) {
  return deck.reverse();
}
