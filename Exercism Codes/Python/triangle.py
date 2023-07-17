def equilateral(sides):
    return len(set(sides)) == 1 and inequality(sides)


def isosceles(sides):
    return len(set(sides)) in [1, 2] and inequality(sides)


def scalene(sides):
    return len(set(sides)) == 3 and inequality(sides)


def inequality(s):
    return s[0] + s[1] >= s[2] and s[1] + s[2] >= s[0] and s[0] + s[2] >= s[1] and all(x > 0 for x in s)
