export const abilityModifier = (n) => {
    if(n > 18) throw new Error('Ability scores can be at most 18');
    if(n < 3) throw new Error('Ability scores must be at least 3');
    return Math.floor((n - 10) / 2);
};
export class Character {
    constructor() {
        this.str = Character.rollAbility();
        this.dex = Character.rollAbility();
        this.con = Character.rollAbility();
        this.int = Character.rollAbility();
        this.wis = Character.rollAbility();
        this.cha = Character.rollAbility();
    }

    static rollAbility() {
        return [...new Array(4).keys()]
      .map(n => Math.ceil(Math.random() * 6))
      .toSorted()
      .slice(1)
      .reduce((a, c) => a + c, 0);
    }
    get strength() {
        return this.str;
    }
    get dexterity() {
        return this.dex;
    }
    get constitution() {
        return this.con;
    }
    get intelligence() {
        return this.int;
    }
    get wisdom() {
        return this.wis;
    }
    get charisma() {
        return this.cha;
    }
    get hitpoints() {
        return 10 + abilityModifier(this.constitution);
    }
}
