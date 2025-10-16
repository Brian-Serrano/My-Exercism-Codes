def square_root(number):
    x = number
    while True:
        x = 0.5 * (x + (number / x))

        if abs(0.5 * (x + (number / x)) - x) < 0.00001:
            break

    return int(x)