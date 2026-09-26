import java.util.ArrayList;
import java.util.List;

class Allergies {
    private int score;
    public Allergies(int score) {
        this.score = score % 256;
    }

    public boolean isAllergicTo(Allergen allergen) {
        return getList().contains(allergen);
    }

    public List<Allergen> getList() {
        List<Allergen> allergens = new ArrayList<>();
        Allergen[] allergenList = Allergen.values();
        while(score > 0) {
            for(int i = allergenList.length - 1; i >= 0; i--) {
                if(score >= allergenList[i].getScore()) {
                    allergens.add(0, allergenList[i]);
                    score -= allergenList[i].getScore();
                    break;
                }
            }
        }
        return allergens;
    }
}
