import java.util.Random;
import java.util.List;

class DnDCharacter {
    private Random random = new Random();
    private int strength = ability(rollDice());
    private int dexterity = ability(rollDice());
    private int constitution = ability(rollDice());
    private int intelligence = ability(rollDice());
    private int wisdom = ability(rollDice());
    private int charisma = ability(rollDice());

    List<Integer> rollDice() {
        return random.ints(4, 1, 7).boxed().toList();
    }
    
    int ability(List<Integer> nums) {
        return nums.stream().sorted().skip(1).mapToInt(Integer::intValue).sum();
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
