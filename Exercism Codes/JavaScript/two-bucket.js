//
// This is only a SKELETON file for the 'Two Bucket' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class TwoBucket {
  constructor(bucketOne, bucketTwo, goal, startBucket) {
    this.bucketOne = bucketOne;
    this.bucketTwo = bucketTwo;
    this.goal = goal;
    this.startBucket = startBucket;
    this.totalMoves = 0;
    this.bucketList = [new Bucket(0, 0, this.startBucket)];
    this.visitedBuckets = new Set(this.bucketList.map(b => b.key()));

    while (!this.bucketList.some(buck => buck.bucketOne == goal || buck.bucketTwo == goal)) {
      this.totalMoves++;
      this.bucketList = this.bucketList.flatMap(buck => this.placeWater(buck)).filter(buck => !this.visitedBuckets.has(buck.key()));

      if (this.bucketList.length == 0) {
        throw new Error("impossible");
      }

      this.bucketList.forEach(buck => this.visitedBuckets.add(buck.key()));
    }

    const data = this.bucketList.filter(buck => buck.bucketOne == goal || buck.bucketTwo == goal)[0];
    this.result = {
      moves: this.totalMoves,
      goalBucket: data.bucketOne == goal ? "one" : "two",
      otherBucket: data.bucketOne != goal ? data.bucketOne : data.bucketTwo
    };
  }

  solve() {
    return this.result;
  }

  placeWater(buck) {
    const buckets = [];
    if (this.totalMoves <= 1) {
      if (this.startBucket == "one") {
        this.bucketOneAction(buck, buckets);
      }
      else if (this.startBucket == "two") {
        this.bucketTwoAction(buck, buckets);
      }
    }
    else {
      this.bucketOneAction(buck, buckets);
      this.bucketTwoAction(buck, buckets);
    }
    return buckets.filter(bu => this.startBucket == "one" ? !(bu.bucketOne == 0 && bu.bucketTwo == this.bucketTwo) : !(bu.bucketTwo == 0 && bu.bucketOne == this.bucketOne));
  }

  bucketOneAction(buck, buckets) {
    if (buck.bucketOne != this.bucketOne) {
      buckets.push(new Bucket(this.bucketOne, buck.bucketTwo, "one"));
    }
    if (buck.bucketOne != 0) {
      buckets.push(new Bucket(0, buck.bucketTwo, "one"));
    }
    if (buck.bucketTwo != this.bucketTwo && buck.bucketOne != 0) {
      const liter = Math.min(buck.bucketOne, this.bucketTwo - buck.bucketTwo);
      buckets.push(new Bucket(buck.bucketOne - liter, buck.bucketTwo + liter, "one"));
    }
  }

  bucketTwoAction(buck, buckets) {
    if (buck.bucketTwo != this.bucketTwo) {
      buckets.push(new Bucket(buck.bucketOne, this.bucketTwo, "two"));
    }
    if (buck.bucketTwo != 0) {
      buckets.push(new Bucket(buck.bucketOne, 0, "two"));
    }
    if (buck.bucketOne != this.bucketOne && buck.bucketTwo != 0) {
      const liter = Math.min(buck.bucketTwo, this.bucketOne - buck.bucketOne);
      buckets.push(new Bucket(buck.bucketOne + liter, buck.bucketTwo - liter, "two"));
    }
  }
}

class Bucket {
  constructor(bucketOne, bucketTwo, currentBucket) {
    this.bucketOne = bucketOne;
    this.bucketTwo = bucketTwo;
    this.currentBucket = currentBucket;
  }

  key() {
    return `${this.bucketOne},${this.bucketTwo},${this.currentBucket}`;
  }
}
