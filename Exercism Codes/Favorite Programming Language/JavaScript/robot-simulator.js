//
// This is only a SKELETON file for the 'Robot Simulator' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class InvalidInputError extends Error {
  constructor(message) {
    super();
    this.message = message || 'Invalid Input';
  }
}

export class Robot {
  constructor() {
    this.coord = [0, 0];
    this.bear = "north";
  }
  
  get bearing() {
    return this.bear;
  }

  get coordinates() {
    return this.coord;
  }

  place({ x, y, direction }) {
    if (!["north", "south", "east", "west"].includes(direction)) {
      throw new InvalidInputError();
    }
    
    this.coord = [x, y];
    this.bear = direction;
  }

  evaluate(instructions) {
    for (let i = 0; i < instructions.length; i++) {
      if (instructions[i] == "L") {
        this.turnLeft();
      }
      else if (instructions[i] == "R") {
        this.turnRight();
      }
      else if (instructions[i] == "A") {
        this.advance();
      }
    }
  }

  turnLeft() {
    if (this.bear == "north") {
      this.bear = "west";
    }
    else if (this.bear == "east") {
      this.bear = "north";
    }
    else if (this.bear == "south") {
      this.bear = "east";
    }
    else if (this.bear == "west") {
      this.bear = "south";
    }
  }

  turnRight() {
    if (this.bear == "north") {
      this.bear = "east";
    }
    else if (this.bear == "east") {
      this.bear = "south";
    }
    else if (this.bear == "south") {
      this.bear = "west";
    }
    else if (this.bear == "west") {
      this.bear = "north";
    }
  }

  advance() {
    if (this.bear == "north") {
      this.coord = [this.coord[0], this.coord[1] + 1];
    }
    else if (this.bear == "east") {
      this.coord = [this.coord[0] + 1, this.coord[1]];
    }
    else if (this.bear == "south") {
      this.coord = [this.coord[0], this.coord[1] - 1];
    }
    else if (this.bear == "west") {
      this.coord = [this.coord[0] - 1, this.coord[1]];
    }
  }
}
