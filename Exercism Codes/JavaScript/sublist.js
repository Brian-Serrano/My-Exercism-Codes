export class List {
  constructor(values = []) {
    this.values = values;
  }

  compare(other) {
    if (this.equal(this.values, other.values)) {
      return "EQUAL";
    }
    if (this.values.length < other.values.length && this.sublist(other.values, this.values)) {
      return "SUBLIST";
    }
    if (this.values.length > other.values.length && this.sublist(this.values, other.values)) {
      return "SUPERLIST";
    }

    return "UNEQUAL";
  }

  sublist(listOne, listTwo) {
    for (let x = 0; x <= listOne.length - listTwo.length; x++) {
      if (this.equal(listOne.slice(x, x + listTwo.length), listTwo)) {
        return true;
      }
    }
    return false;
  }

  equal(listOne, listTwo) {
    if (listOne.length !== listTwo.length) {
      return false;
    }

    for (let x = 0; x < listOne.length; x++) {
      if (listOne[x] !== listTwo[x]) {
        return false;
      }
    }

    return true;
  }
}