def prime(number):
    if number <= 0:
        raise ValueError("there is no zeroth prime")
    if number == 1:
        return 2

    i, count = 1, 1

    while count != number:
        i += 2
        if is_prime(i):
            count += 1

    return i


def is_prime(number):
    return not any(number % x == 0 for x in range(3, int(number ** 0.5) + 1, 2))