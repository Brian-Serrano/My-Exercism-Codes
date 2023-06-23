import java.util.Random;

class DnDCharacter {
    private Random random = new Random();
    private int strength = ability();
    private int dexterity = ability();
    private int constitution = ability();
    private int intelligence = ability();
    private int wisdom = ability();
    private int charisma = ability();
    
    int ability() {
        return random.ints(4, 1, 7).sorted().skip(1).sum();
    }
    int modifier(int input) {
        return Math.floorDiv(input - 10, 2);
    }
    int getStrength() {
        return strength;
    }
    int getDexterity() {
        return dexterity;
    }
    int getConstitution() {
        return constitution;
    }
    int getIntelligence() {
        return intelligence;
    }
    int getWisdom() {
        return wisdom;
    }
    int getCharisma() {
        return charisma;
    }
    int getHitpoints() {
        return 10 + modifier(constitution);
    }
}