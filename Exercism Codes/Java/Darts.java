import java.lang.Math;
class Darts {
    int score(double xOfDart, double yOfDart) {
        double dist = Math.sqrt(xOfDart*xOfDart + yOfDart*yOfDart);
        if (dist > 10) return 0;
        else if (dist > 5) return 1;
        else if (dist > 1) return 5;
        else return 10;
    }
}