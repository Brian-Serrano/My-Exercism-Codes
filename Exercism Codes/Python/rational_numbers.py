def GCF(a, b):
    while b != 0:
        temp = b
        b = a % b
        a = temp
    return a

def LCM(a, b):
    return (a * b) // GCF(a, b)

def simplify(num, den):
    gcf = GCF(num, den)
    return Rational(num // gcf, den // gcf)

class Rational:
    def __init__(self, num, den):
        gcf = GCF(num, den)
        self.num = num // gcf
        self.den = den // gcf

    def __eq__(self, other):
        return self.num == other.num and self.den == other.den

    def __repr__(self):
        return f'{self.num}/{self.den}'

    def __add__(self, other):
        lcm = LCM(self.den, other.den)
        return simplify(self.num * (lcm / self.den) + other.num * (lcm / other.den), lcm)

    def __sub__(self, other):
        lcm = LCM(self.den, other.den)
        return simplify(self.num * (lcm / self.den) - other.num * (lcm / other.den), lcm)

    def __mul__(self, other):
        return simplify(self.num * other.num, self.den * other.den)

    def __truediv__(self, other):
        return simplify(self.num * other.den, other.num * self.den)

    def __abs__(self):
        return simplify(abs(self.num), abs(self.den))

    def __pow__(self, power):
        a = pow(self.num, abs(power))
        b = pow(self.den, abs(power))
        return simplify(a, b) if power >= 0 else simplify(b, a)

    def __rpow__(self, base):
        return pow(pow(base, self.num), 1.0 / self.den)
