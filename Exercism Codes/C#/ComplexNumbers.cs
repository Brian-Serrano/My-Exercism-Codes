public struct ComplexNumber
{
    private double real, imaginary;

    public ComplexNumber(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double Real()
    {
        return real;
    }

    public double Imaginary()
    {
        return imaginary;
    }

    public ComplexNumber Mul(ComplexNumber other)
    {
        return new ComplexNumber(
                (real * other.real) - (imaginary * other.imaginary),
                (real * other.imaginary) + (imaginary * other.real)
            );
    }

    public ComplexNumber Mul(double other)
    {
        return new ComplexNumber(real * other, imaginary * other);
    }

    public ComplexNumber Add(ComplexNumber other)
    {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    public ComplexNumber Add(double other)
    {
        return new ComplexNumber(real + other, imaginary);
    }

    public ComplexNumber Sub(ComplexNumber other)
    {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    public ComplexNumber Sub(double other)
    {
        return new ComplexNumber(real - other, imaginary);
    }

    public ComplexNumber Div(ComplexNumber other)
    {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        return new ComplexNumber(
                ((real * other.real) + (imaginary * other.imaginary)) / denominator,
                ((imaginary * other.real) - (real * other.imaginary)) / denominator
            );
    }

    public ComplexNumber Div(double other)
    {
        return new ComplexNumber(real / other, imaginary / other);
    }

    public double Abs()
    {
        return Math.Sqrt(real * real + imaginary * imaginary);
    }

    public ComplexNumber Conjugate()
    {
        return new ComplexNumber(real, -imaginary);
    }
    
    public ComplexNumber Exp()
    {
        return new ComplexNumber(Math.Exp(real) * Math.Cos(imaginary), Math.Exp(real) * Math.Sin(imaginary));
    }
}