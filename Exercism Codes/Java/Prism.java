import java.util.*;

public class Prism {

    public record LaserInfo(double x, double y, double angle) {
    }

    public record PrismInfo(int id, double x, double y, double angle) {
    }

    public static List<Integer> findSequence(LaserInfo laser, List<PrismInfo> prisms) {
        double cx = laser.x;
        double cy = laser.y;
        double cAngle = toPositiveAngle(laser.angle);
        List<Integer> result = new ArrayList<>();

        while (true) {
            List<PrismInfo> paths = new ArrayList<>();
            for (PrismInfo prism : prisms) {
                boolean notSameLocation = !(prism.y - cy == 0 && prism.x - cx == 0);
                double rAngle = Math.atan2(prism.y - cy, prism.x - cx);
                double angle = toPositiveAngle(Math.toDegrees(rAngle));
                double difference = Math.abs(angle - cAngle);
                boolean angleEqual = Math.min(difference, 360.0 - difference) <= 0.005;
                if (notSameLocation && angleEqual) {
                    paths.add(prism);
                }
            }
            if (paths.isEmpty())
                break;

            if (paths.size() > 1) {
                double x = cx;
                double y = cy;

                PrismInfo nearestPrism = paths.stream()
                        .min(Comparator.comparingDouble(p -> getMin(p, x, y)))
                        .orElseThrow();

                result.add(nearestPrism.id);
                cx = nearestPrism.x;
                cy = nearestPrism.y;
                cAngle = toPositiveAngle(cAngle + nearestPrism.angle);
            }
            else {
                result.add(paths.getFirst().id);
                cx = paths.getFirst().x;
                cy = paths.getFirst().y;
                cAngle = toPositiveAngle(cAngle + paths.getFirst().angle);
            }
        }

        return result;
    }

    private static double toPositiveAngle(double angle) {
        double value = angle - Math.floor(angle / 360.0) * 360.0;
        return Math.round(value * 1000.0) / 1000.0;
    }
    
    private static double getMin(PrismInfo p, double currentX, double currentY) {
        double dx = p.x - currentX;
        double dy = p.y - currentY;
        return dx * dx + dy * dy;
    }
}