class Rational {

    private int numerator, denominator;

    Rational(int numerator, int denominator) {
        simplifyInConstructor(numerator, denominator);
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    Rational simplify(int numerator, int denominator) {
        int gcf = GCF(numerator, denominator);
        return new Rational(numerator / gcf, denominator / gcf);
    }

    void simplifyInConstructor(int numerator, int denominator) {
        int gcf = GCF(numerator, denominator);
        this.numerator = numerator / gcf;
        this.denominator = denominator / gcf;
    }

    int GCF(int a, int b) {
        while (b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    int LCM(int a, int b) {
        return (a * b) / GCF(a, b);
    }

    Rational add(Rational other) {
        int lcm = LCM(denominator, other.denominator);
        return simplify(numerator * (lcm / denominator) + other.numerator * (lcm / other.denominator), lcm);
    }

    Rational subtract(Rational other) {
        int lcm = LCM(denominator, other.denominator);
        return simplify(numerator * (lcm / denominator) - other.numerator * (lcm / other.denominator), lcm);
    }

    Rational multiply(Rational other) {
        return simplify(numerator * other.numerator, denominator * other.denominator);
    }

    Rational divide(Rational other) {
        return simplify(numerator * other.denominator, other.numerator * denominator);
    }

    Rational abs() {
        return simplify(Math.abs(numerator), Math.abs(denominator));
    }

    Rational pow(int num) {
        return simplify((int)Math.pow(numerator, Math.abs(num)),(int)Math.pow(denominator, Math.abs(num)));
    }

    double exp(double num) {
        return Math.pow(Math.pow(num, numerator), 1.0 / denominator) + 1e-15;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Rational other = (Rational) obj;
        return this.getNumerator() == other.getNumerator()
            && this.getDenominator() == other.getDenominator();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.getNumerator();
        result = prime * result + this.getDenominator();

        return result;
    }
}
