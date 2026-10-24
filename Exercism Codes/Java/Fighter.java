class Fighter {

    boolean isVulnerable() {
        return false;
    }
}

class Warrior extends Fighter {

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    boolean isVulnerable() {
        return false;
    }

    int getDamagePoints(Fighter wizard) {
        return wizard.isVulnerable() ? 10 : 6;
    }
}

class Wizard extends Fighter {
    private boolean spell = false;

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }

    @Override
    boolean isVulnerable() {
        return !spell;
    }

    int getDamagePoints(Fighter warrior) {
        return !isVulnerable() ? 12 : 3;
    }

    void prepareSpell() {
        spell = true;
    }

}
