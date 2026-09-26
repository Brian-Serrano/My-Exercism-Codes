import java.util.Random;

class CaptainsLog {

    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    private Random random;

    CaptainsLog(Random random) {
        this.random = random;
    }

    char randomPlanetClass() {
        return PLANET_CLASSES[random.nextInt(0, 10)];
    }

    String randomShipRegistryNumber() {
        return "NCC-" + random.nextInt(1000, 10000);
    }

    double randomStardate() {
        return random.nextDouble(41000.0, 42000.0);
    }
}
