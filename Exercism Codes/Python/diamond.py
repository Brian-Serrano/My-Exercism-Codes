def rows(letter):
    result = []
    for x in range(ord("A"), ord(letter) + 1):
        result.append(line(letter, x))

    for x in range(ord(letter) - 1, ord("A") - 1, -1):
        result.append(line(letter, x))

    return result

def line(letter, x):
    pad_1 = (" " * ((ord(letter) - ord("A")) - (x - ord("A"))))
    pad_2 = (" " * (x - ord("A")))
    first_half_line = pad_1 + chr(x) + pad_2
    second_half_line = pad_2 + chr(x) + pad_1
    return first_half_line + second_half_line[1:]