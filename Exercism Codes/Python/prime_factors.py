def factors(value):
    result = []
    while value > 1:
        for x in range(2, value + 1):
            if value % x == 0:
                result.append(x)
                value //= x
                break

    return result