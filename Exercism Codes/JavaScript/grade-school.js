//
// This is only a SKELETON file for the 'Grade School' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class GradeSchool {
  constructor() {
    this.students = {};
  }
  
  roster() {
    return Object.values(this.students).flatMap(x => x.toSorted());
  }

  add(name, grade) {
    if (!this.roster().includes(name)) {
      if (!(grade in this.students)) {
        this.students[grade] = [];
      }
      this.students[grade].push(name);
      return true;
    }
    else {
      return false;
    }
  }

  grade(gradeNumber) {
    return gradeNumber in this.students ? this.students[gradeNumber].toSorted() : [];
  }
}
