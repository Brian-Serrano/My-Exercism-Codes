import math


def score(x, y):
    dst = math.sqrt(x * x + y * y)
    if dst > 10:
        return 0
    elif dst > 5:
        return 1
    elif dst > 1:
        return 5
    else:
        return 10
