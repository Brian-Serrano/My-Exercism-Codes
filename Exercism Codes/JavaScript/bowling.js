//
// This is only a SKELETON file for the 'Bowling' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Bowling {
  constructor() {
    this.scr = 0;
    this.numOfThrows = 0;
    this.numOfGame = 0;
    this.gameScore = 0;
    this.bonus = 0;
  }
  
  roll(pins) {
    if (pins < 0) {
      throw new Error("Negative roll is invalid");
    }
    if (this.numOfGame >= 10) {
      if (this.bonus == 0) {
        throw new Error("Cannot roll after game is over");
      }
      else {
        this.scr += this.bonus > 2 ? pins * 2 : pins;
        this.bonus -= this.bonus > 2 ? 2 : 1;
        this.gameScore += pins;
        if (this.gameScore > 10) {
          throw new Error("Pin count exceeds pins on the lane");
        }
        if (this.gameScore == 10) {
          this.gameScore = 0;
        }
      }
    }
    else {
      this.scr += pins * (this.bonus > 0 ? (this.bonus > 2 ? 3 : 2) : 1);
      this.bonus -= this.bonus == 0 ? 0 : (this.bonus > 2 ? 2 : 1);
      this.gameScore += pins;
      this.numOfThrows++;
      if (this.gameScore > 10) {
        throw new Error("Pin count exceeds pins on the lane");
      }
      if (this.numOfThrows == 1 && this.gameScore == 10) {
        this.bonus += 2;
        this.numOfGame++;
        this.numOfThrows = 0;
        this.gameScore = 0;
      }
      if (this.numOfThrows == 2) {
        this.bonus += this.gameScore == 10 ? 1 : 0;
        this.numOfGame++;
        this.numOfThrows = 0;
        this.gameScore = 0;
      }
    }
  }

  score() {
    if (this.numOfGame == 10 && this.bonus == 0) {
      return this.scr;
    }
    else {
      throw new Error("Score cannot be taken until the end of the game");
    }
  }
}
