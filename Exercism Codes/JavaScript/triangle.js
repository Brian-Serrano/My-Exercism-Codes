export class Triangle {
    constructor(...sides) {
        this.s = sides;
    }
    static zeroAndInequality(s) {
        return s.every(c => c > 0) 
          && s[0] + s[1] >= s[2] 
          && s[1] + s[2] >= s[0] 
          && s[0] + s[2] >= s[1];
    }
    get isEquilateral() {
        return this.s[0] == this.s[1] 
          && this.s[1] == this.s[2] 
          && Triangle.zeroAndInequality(this.s);
    }
    get isIsosceles() {
        return (this.s[0] == this.s[1] 
          || this.s[1] == this.s[2] 
          || this.s[0] == this.s[2]) 
          && Triangle.zeroAndInequality(this.s);
    }
    get isScalene() {
        return this.s[0] != this.s[1] 
          && this.s[1] != this.s[2] 
          && this.s[0] != this.s[2] 
          && Triangle.zeroAndInequality(this.s);
    }
}
