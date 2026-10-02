//
// This is only a SKELETON file for the 'Poker' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const rankValues = {
  "1": 1, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7,
  "8": 8, "9": 9, "10": 10, "J": 11, "Q": 12, "K": 13, "A": 14
};

export const bestHands = (hands) => {
  const handInfo = [];

  for (const s of hands) {
    const straightFlush = isStraightFlush(s);
    const square = isSquare(s);
    const fullHouse = isFullHouse(s);
    const flush = isFlush(s);
    const straight = isStraight(s);
    const threeOfKind = isThreeOfKind(s);
    const twoPair = isTwoPair(s);
    const onePair = isOnePair(s);

    if (straightFlush[1])
      handInfo.push(new Hand(s, 9, straightFlush[0]));
    else if (square[1])
      handInfo.push(new Hand(s, 8, square[0]));
    else if (fullHouse[1])
      handInfo.push(new Hand(s, 7, fullHouse[0]));
    else if (flush[1])
      handInfo.push(new Hand(s, 6, flush[0]));
    else if (straight[1])
      handInfo.push(new Hand(s, 5, straight[0]));
    else if (threeOfKind[1])
      handInfo.push(new Hand(s, 4, threeOfKind[0]));
    else if (twoPair[1])
      handInfo.push(new Hand(s, 3, twoPair[0]));
    else if (onePair[1])
      handInfo.push(new Hand(s, 2, onePair[0]));
    else
      handInfo.push(new Hand(s, 1, getRank(s)));
  }
  let max = handInfo[0].category;
  for (const hand of handInfo) {
    if (hand.category > max) {
      max = hand.category;
    }
  }

  const maxHands = handInfo.filter(h => h.category == max);
  if (maxHands.length > 1) {
    const hand = [];
    const ranks = [];
    for (const maxHand of maxHands) {
      hand.push(maxHand.hand);
      ranks.push(maxHand.ranks);
    }
    return highest(hand, ranks);
  }
  return maxHands.map(h => h.hand);
};

const isStraightFlush = (hand) => {
  const rank = getRank(hand);
  const suit = getSuit(hand);
  checkAce(rank);
  return [
    [rank[rank.length - 1]],
    new Set(suit).size == 1 && range(1, 4).every(n => rank[n] + 1 == rank[n - 1])
  ];
};

const isSquare = (hand) => {
  const rank = getRank(hand);
  const first = rank[0];
  const last = rank[rank.length - 1];
  const num1 = rank.filter(x => x == first).length;
  const num2 = rank.filter(x => x == last).length;
  return [[num1 == 4 ? first : last, num1 == 4 ? last : first], num2 == 4 || num1 == 4];
};

const isFullHouse = (hand) => {
  const rank = getRank(hand);
  const first = rank[0];
  const last = rank[rank.length - 1];
  const num1 = rank.filter(x => x == first).length;
  const num2 = rank.filter(x => x == last).length;
  return [
    [num1 == 3 ? first : last, num1 == 3 ? last : first],
    arraysEqual([2, 3], [num2, num1])
  ]
};

const isFlush = (hand) => {
  const suit = getSuit(hand);
  return [getRank(hand), new Set(suit).size == 1];
};

const isStraight = (hand) => {
  const rank = getRank(hand);
  checkAce(rank);
  return [
    [rank[rank.length - 1]],
    range(1, 4).every(n => rank[n] + 1 == rank[n - 1])
  ];
};

const isThreeOfKind = (hand) => {
  const rank = getRank(hand);
  const middle = rank[Math.floor(rank.length / 2)];
  const num1 = rank.filter(x => x == middle).length;
  return [
    [middle, ...rank.filter(c => rank.filter(x => x == c).length == 1)],
    num1 == 3
  ];
};

const isTwoPair = (hand) => {
  const rank = getRank(hand);
  const second = rank[1];
  const fourth = rank[3];
  const num1 = rank.filter(x => x == second).length;
  const num2 = rank.filter(x => x == fourth).length;
  return [
    [second, fourth, rank.filter(c => rank.filter(x => x == c).length == 1)[0]],
    num1 == 2 && num2 == 2
  ];
};

const isOnePair = (hand) => {
  const rank = getRank(hand);
  const pairs = [...new Set(rank)].filter(c => rank.filter(x => x == c).length == 2);
  const result = [];
  if (pairs.length > 0) {
    result.push(pairs[0]);
  }
  return [
    [...result, ...rank.filter(c => rank.filter(x => x == c).length == 1)],
    pairs.length == 1
  ];
};

const highest = (hands, ranks) => {
  const result = [];
  let highest = ranks[0];
  for (const rank of ranks) {
    if (larger(rank, highest)) {
      highest = rank;
    }
  }
  for (let i = 0; i < ranks.length; i++) {
    if (arraysEqualOrdered(highest, ranks[i])) {
      result.push(hands[i]);
    }
  }
  return result;
};

const checkAce = (rank) => {
  if (range(2, 4).every(n => rank[n] + 1 == rank[n - 1]) && rank[1] == 5 && rank[0] == 14) {
    rank[0] = 1;
    rank.sort((a, b) => b - a);
  }
};

const larger = (lst1, lst2) => {
  for (let i = 0; i < lst1.length; i++) {
    if (lst1[i] > lst2[i])
      return true;
    if (lst1[i] < lst2[i])
      return false;
  }
  return false;
};

const getRank = (hand) => {
  return hand.split(" ").map(c => rankValues[c.substring(0, c.length - 1)]).toSorted((a, b) => b - a);
};

const getSuit = (hand) => {
  return hand.split(" ").map(c => c[c.length - 1]);
};

const range = (start, stop, step = 1) =>
  Array.from({ length: (stop - start) / step + 1 }, (_, i) => start + i * step);

const arraysEqual = (arr1, arr2) => {
  if (arr1.length !== arr2.length) return false;
  
  const sorted1 = [...arr1].sort();
  const sorted2 = [...arr2].sort();
  
  return sorted1.every((val, index) => val === sorted2[index]);
};

const arraysEqualOrdered = (arr1, arr2) => {
  if (arr1.length !== arr2.length) return false;
  return arr1.every((val, index) => val === arr2[index]);
};

class Hand {
  constructor(hand, category, ranks) {
    this.hand = hand;
    this.category = category;
    this.ranks = ranks;
  }
}