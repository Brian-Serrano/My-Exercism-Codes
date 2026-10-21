import re


def convert(input_grid):
    if len(input_grid) % 4 != 0:
        raise ValueError("Number of input lines is not a multiple of four")
    if len(input_grid[0]) % 3 != 0:
        raise ValueError("Number of input columns is not a multiple of three")

    readers = [
        lambda t: t == [" _ ", "| |", "|_|", "   "],
        lambda t: t == ["   ", "  |", "  |", "   "],
        lambda t: t == [" _ ", " _|", "|_ ", "   "],
        lambda t: t == [" _ ", " _|", " _|", "   "],
        lambda t: t == ["   ", "|_|", "  |", "   "],
        lambda t: t == [" _ ", "|_ ", " _|", "   "],
        lambda t: t == [" _ ", "|_ ", "|_|", "   "],
        lambda t: t == [" _ ", "  |", "  |", "   "],
        lambda t: t == [" _ ", "|_|", "|_|", "   "],
        lambda t: t == [" _ ", "|_|", " _|", "   "]
    ]

    cropped = crop(input_grid)
    s = ""
    for j in range(len(cropped)):
        for k in range(len(cropped[j])):
            for i in range(len(readers) + 1):
                if i == len(readers):
                    s += "?"
                else:
                    if readers[i](cropped[j][k]):
                        s += str(i)
                        break

        if j != len(cropped) - 1:
            s += ","

    return s

def crop(text):
    res = []
    for i in range(0, len(text), 4):
        res.append(text[i : i + 4])

    return [zip_input([re.findall(".{3}", s) for s in r]) for r in res]

def zip_input(text):
    return [[l[t] for l in text] for t in range(0, len(text[0]))]