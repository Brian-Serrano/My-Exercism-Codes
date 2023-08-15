class ComplexNumber {

    private final double real;
    private final double imag;

    ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    double getReal() {
        return real;
    }

    double getImag() {
        return imag;
    }

    ComplexNumber times(ComplexNumber cn) {
        return new ComplexNumber((real * cn.real) - (imag * cn.imag), (real * cn.imag) + (imag * cn.real));
    }

    ComplexNumber add(ComplexNumber cn) {
        return new ComplexNumber(real + cn.real, imag + cn.imag);
    }

    ComplexNumber minus(ComplexNumber cn) {
        return new ComplexNumber(real - cn.real, imag - cn.imag);
    }

    ComplexNumber div(ComplexNumber cn) {
        double a2b2sq = cn.real * cn.real + cn.imag * cn.imag;
        return new ComplexNumber(((real * cn.real) + (imag * cn.imag)) / a2b2sq, ((imag * cn.real) - (real * cn.imag)) / a2b2sq);
    }

    double abs() {
        return Math.sqrt(real * real + imag * imag);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, -imag);
    }

    ComplexNumber exponentialOf() {
        return new ComplexNumber(Math.exp(real) * Math.cos(imag), Math.exp(real) * Math.sin(imag));
    }
}