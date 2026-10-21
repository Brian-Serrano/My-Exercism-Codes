//
// This is only a SKELETON file for the 'Camicia' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const simulateGame = (playerA, playerB) => {
  const handA = playerA.map(p => getValue(p));
  const handB = playerB.map(p => getValue(p));
  let turn = "A";
  let pile = [];
  const seen = [];
  let totalTricks = 0;
  let cardsPlayed = 0;
  let currentDebt = 0;

  while (true) {
    if (pile.length == 0) {
      const state = [[...handA], [...handB], turn];
      if (includesState(seen, state)) {
        return {
          status: "loop",
          tricks: totalTricks,
          cards: cardsPlayed
        };
      }
      seen.push(state);
    }

    let activeHand = turn == "A" ? handA : handB;
    let otherHand = turn == "A" ? handB : handA;

    if (activeHand.length == 0) {
      const extraTrick = pile.length == 0 ? 0 : 1;
      return {
        status: "finished",
        tricks: totalTricks + extraTrick,
        cards: cardsPlayed
      };
    }

    const cardVal = activeHand.shift();
    pile.push(cardVal);
    cardsPlayed++;

    if (cardVal > 0) {
      currentDebt = cardVal;
      turn = turn == "A" ? "B" : "A";
    }
    else {
      if (currentDebt > 0) {
        currentDebt--;
        if (currentDebt == 0) {
          for (const p of pile) {
            otherHand.push(p);
          }
          pile = [];
          totalTricks++;

          if (handA.length == 0 || handB.length == 0) {
            return {
              status: "finished",
              tricks: totalTricks,
              cards: cardsPlayed
            };
          }
          turn = turn == "A" ? "B" : "A";
        }
      }
      else {
        turn = turn == "A" ? "B" : "A";
      }
    }
  }
};

const getValue = (card) => {
  switch (card) {
    case "J":
      return 1;
    case "Q":
      return 2;
    case "K":
      return 3;
    case "A":
      return 4;
    default:
      return 0;
  }
};

const includesState = (seen, state) => {
  for (const s of seen) {
    if (equalArrays(s[0], state[0]) && equalArrays(s[1], state[1]) && s[2] == state[2]) {
      return true;
    }
  }
  return false;
};

const equalArrays = (a, b) => 
  a.length === b.length && a.every((val, idx) => val === b[idx]);
