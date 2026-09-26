def is_valid(isbn):
    isbn_10 = isbn.replace("-", "")
    n = len(isbn_10)
    if n != 10:
        return False

    total = 0
    for idx, digit in enumerate(isbn_10):
        m = n - idx
        if idx == n - 1 and digit == "X":
            total += 10 * m
            continue

        if digit.isdigit():
            total += int(digit) * m
        else:
            return False

    return total % 11 == 0