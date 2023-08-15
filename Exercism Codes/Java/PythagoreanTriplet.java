import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PythagoreanTriplet {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PythagoreanTriplet that = (PythagoreanTriplet) o;
        return a == that.a && b == that.b && c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    private List<Triple> triple;
    private int a, b, c;

    public PythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private PythagoreanTriplet(List<Triple> triple) {
        this.triple = triple;
    }

    public static PythagoreanTriplet makeTripletsList() {
        List<Triple> triple = new ArrayList<>();
        for (int a = 1; a <= 15000; a++) {
            for (int b = a; b <= 15000; b++) {
                int cSquared = a * a + b * b;
                int c = (int) Math.sqrt(cSquared);
                if (c * c == cSquared) {
                    triple.add(new Triple(a, b, c));
                }
            }
        }
        return new PythagoreanTriplet(triple);
    }

    public PythagoreanTriplet thatSumTo(int number) {
        triple = triple.stream().filter(t -> t.a + t.b + t.c == number).toList();
        return this;
    }

    public List<PythagoreanTriplet> build() {
        return triple.stream().map(t -> new PythagoreanTriplet(t.a, t.b, t.c)).toList();
    }

    public PythagoreanTriplet withFactorsLessThanOrEqualTo(int limit) {
        triple = triple.stream().filter(t -> t.c <= limit).toList();
        return this;
    }
}

class Triple {

    public int a, b, c;

    public Triple(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}