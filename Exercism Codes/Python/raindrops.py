def convert(number):
    pl = ""
    if number % 3 == 0:
        pl += "Pling"
    if number % 5 == 0:
        pl += "Plang"
    if number % 7 == 0:
        pl += "Plong"
    return pl if len(pl) != 0 else str(number)
