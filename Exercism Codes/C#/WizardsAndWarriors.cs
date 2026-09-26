using System;

abstract class Character
{
    public bool isVul;
    public string characterType;

    protected Character(string characterType)
    {
        this.characterType = characterType;
    }

    public abstract int DamagePoints(Character target);

    public virtual bool Vulnerable() => isVul;

    public override string ToString() => "Character is a " + characterType;
}

class Warrior : Character
{
    public Warrior() : base("Warrior")
    {
    }

    public override int DamagePoints(Character target) => target.isVul ? 10 : 6;
}

class Wizard : Character
{
    public Wizard() : base("Wizard")
    {
        isVul = true;
    }

    public override int DamagePoints(Character target) => target.isVul ? 3 : 12;

    public void PrepareSpell()
    {
        isVul = false;
    }
}
