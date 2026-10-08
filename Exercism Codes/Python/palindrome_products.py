def largest(min_factor, max_factor):
    if min_factor > max_factor:
        raise ValueError("min must be <= max")

    maximum = None
    factors = []

    for i in range(min_factor, max_factor + 1):
        for j in range(i, max_factor + 1):
            product = i * j

            if maximum is not None and product < maximum:
                continue

            if is_palindrome(str(product)):
                if product > (maximum or 0):
                    maximum = product
                    factors = [[i, j]]
                elif product == maximum:
                    factors.append([i, j])

    return (maximum, factors) if maximum is not None else (None, [])


def smallest(min_factor, max_factor):
    if min_factor > max_factor:
        raise ValueError("min must be <= max")

    minimum = None
    factors = []

    for i in range(min_factor, max_factor + 1):
        for j in range(i, max_factor + 1):
            product = i * j

            if minimum is not None and product > minimum:
                continue

            if is_palindrome(str(product)):
                if minimum is None or product < minimum:
                    minimum = product
                    factors = [[i, j]]
                elif product == minimum:
                    factors.append([i, j])

    return (minimum, factors) if minimum is not None else (None, [])


def is_palindrome(num):
    return num == num[::-1]