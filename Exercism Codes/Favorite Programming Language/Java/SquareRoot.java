public class SquareRoot {
    public int squareRoot(int radicand) {
        double x = radicand;
        do {
            x = 0.5 * (x + (radicand / x));
        } while (Math.abs(0.5 * (x + (radicand / x)) - x) >= 0.00001);
        return (int) x;
    }
}
