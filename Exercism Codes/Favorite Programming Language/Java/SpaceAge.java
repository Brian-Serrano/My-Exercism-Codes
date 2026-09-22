class SpaceAge {

    private double seconds;
    
    SpaceAge(double seconds) {
        this.seconds = seconds / 31557600;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return seconds / 1.0;
    }

    double onMercury() {
        return seconds / 0.2408467;
    }

    double onVenus() {
        return seconds / 0.61519726;
    }

    double onMars() {
        return seconds / 1.8808158;
    }

    double onJupiter() {
        return seconds / 11.862615;
    }

    double onSaturn() {
        return seconds / 29.447498;
    }

    double onUranus() {
        return seconds / 84.016846;
    }

    double onNeptune() {
        return seconds / 164.79132;
    }

}