//
// This is only a SKELETON file for the 'Kindergarten Garden' exercise.
// It's been provided as a convenience to get you started writing code faster.
//

const DEFAULT_STUDENTS = [
  'Alice',
  'Bob',
  'Charlie',
  'David',
  'Eve',
  'Fred',
  'Ginny',
  'Harriet',
  'Ileana',
  'Joseph',
  'Kincaid',
  'Larry',
];

const PLANT_CODES = {
  G: 'grass',
  V: 'violets',
  R: 'radishes',
  C: 'clover',
};

export class Garden {
  constructor(diagram, students = DEFAULT_STUDENTS) {
    this.diagram = diagram.split("\n");
    this.students = students.toSorted();
  }

  plants(student) {
    const plnts = [];
    for (const d of this.diagram) {
      const ch = this.students.indexOf(student) * 2;
      plnts.push(PLANT_CODES[d[ch]]);
      plnts.push(PLANT_CODES[d[ch + 1]]);
    }
    return plnts;
  }
}
