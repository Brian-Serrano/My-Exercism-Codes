def roman(number):
    result = ""
    dct = {
        "M": 1000, "CM": 900, "D": 500,
        "CD": 400, "C": 100, "XC": 90,
        "L": 50, "XL": 40, "X": 10,
        "IX": 9, "V": 5, "IV": 4, "I": 1
    }

    while number > 0:
        for k, v in dct.items():
            if number >= v:
                result += k
                number -= v
                break

    return result