import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestTrack {
    public static void race(RemoteControlCar car) {
        car.drive();
    }
    
    public static List<ProductionRemoteControlCar> getRankedCars(ProductionRemoteControlCar prc1,
                                                                 ProductionRemoteControlCar prc2) {
        var rankedCars = new ArrayList<ProductionRemoteControlCar>();
        rankedCars.add(prc1);
        rankedCars.add(prc2);
        return rankedCars
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}