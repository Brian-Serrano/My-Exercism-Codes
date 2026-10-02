//
// This is only a SKELETON file for the 'Allergies' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const allergen = {
    "eggs": 1,
    "peanuts": 2,
    "shellfish": 4,
    "strawberries": 8,
    "tomatoes": 16,
    "chocolate": 32,
    "pollen": 64,
    "cats": 128
};

export class Allergies {
  constructor(score) {
    this.score = score % 256;
  }

  list() {
    const allergens = [];
    const allergenList = Object.keys(allergen);
    while (this.score > 0) {
      for (let i = allergenList.length - 1; i >= 0; i--) {
        if (this.score >= allergen[allergenList[i]]) {
          allergens.unshift(allergenList[i]);
          this.score -= allergen[allergenList[i]];
          break;
        }
      }
    }
    return allergens;
  }

  allergicTo(item) {
    return this.list().includes(item);
  }
}
