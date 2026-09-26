import java.util.ArrayList;
import java.util.List;

class KindergartenGarden {

    private final String[] garden;

    KindergartenGarden(String garden) {
        this.garden = garden.split("\n");
    }

    List<Plant> getPlantsOfStudent(String student) {
        List<Plant> plants = new ArrayList<>();
        for(String g : garden) {
            int ch = (student.charAt(0) - 65) * 2;
            plants.add(Plant.getPlant(g.charAt(ch)));
            plants.add(Plant.getPlant(g.charAt(ch + 1)));
        }
        return plants;
    }

}
