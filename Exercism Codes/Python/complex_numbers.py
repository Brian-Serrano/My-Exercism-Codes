import math


class ComplexNumber:
    def __init__(self, real, imaginary):
        self.real = real
        self.imaginary = imaginary

    def __eq__(self, other):
        return self.real == other.real and self.imaginary == other.imaginary

    def __add__(self, other):
        if isinstance(other, ComplexNumber):
            return ComplexNumber(self.real + other.real, self.imaginary + other.imaginary)

        return ComplexNumber(self.real + other, self.imaginary)

    def __radd__(self, other):
        return ComplexNumber(self.real + other, self.imaginary)

    def __mul__(self, other):
        if isinstance(other, ComplexNumber):
            return ComplexNumber(
                (self.real * other.real) - (self.imaginary * other.imaginary),
                (self.real * other.imaginary) + (self.imaginary * other.real)
            )

        return ComplexNumber(self.real * other, self.imaginary * other)

    def __rmul__(self, other):
        return ComplexNumber(self.real * other, self.imaginary * other)

    def __sub__(self, other):
        if isinstance(other, ComplexNumber):
            return ComplexNumber(self.real - other.real, self.imaginary - other.imaginary)

        return ComplexNumber(self.real - other, self.imaginary)

    def __rsub__(self, other):
        return ComplexNumber(other - self.real, -self.imaginary)

    def __truediv__(self, other):
        if isinstance(other, ComplexNumber):
            denominator = other.real * other.real + other.imaginary * other.imaginary
            return ComplexNumber(
                ((self.real * other.real) + (self.imaginary * other.imaginary)) / denominator,
                ((self.imaginary * other.real) - (self.real * other.imaginary)) / denominator
            )

        return ComplexNumber(self.real / other, self.imaginary / other)

    def __rtruediv__(self, other):
        denominator = self.real * self.real + self.imaginary * self.imaginary

        return ComplexNumber(other * self.real / denominator, -other * self.imaginary / denominator)

    def __abs__(self):
        return math.sqrt(self.real * self.real + self.imaginary * self.imaginary)

    def conjugate(self):
        return ComplexNumber(self.real, -self.imaginary)

    def exp(self):
        return ComplexNumber(math.exp(self.real) * math.cos(self.imaginary), math.exp(self.real) * math.sin(self.imaginary))
