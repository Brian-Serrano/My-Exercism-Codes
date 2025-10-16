def label(colors):
    dct = {
        "black": 0, "brown": 1, "red": 2, "orange": 3,
        "yellow": 4, "green": 5, "blue": 6, "violet": 7,
        "grey": 8, "white": 9
    }

    result = int(str(dct[colors[0]]) + str(dct[colors[1]]) + "0" * (dct[colors[2]]))

    if result >= 1000000000:
        return str(result // 1000000000) + " gigaohms"
    elif result >= 1000000:
        return str(result // 1000000) + " megaohms"
    elif result >= 1000:
        return str(result // 1000) + " kiloohms"
    else:
        return str(result) + " ohms"