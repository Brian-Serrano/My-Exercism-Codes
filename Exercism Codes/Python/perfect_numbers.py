def classify(number):
    if number <= 0:
        raise ValueError("Classification is only possible for positive integers.")
    aliquot = sum(x for x in range(1, number) if number % x == 0)
    return "abundant" if aliquot > number else ("perfect" if aliquot == number else "deficient")
