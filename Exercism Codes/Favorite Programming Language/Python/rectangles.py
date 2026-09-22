import re

ex = re.compile("^\\+[+-]*\\+$")
it = re.compile("^[+|].*[+|]$")


def rectangles(strings):
    count = 0
    for i in range(len(strings)):
        for j in range(len(strings[i])):
            if strings[i][j] == '+':
                for k in range(j + 1, len(strings[i])):
                    if ex.match(strings[i][j:k + 1]):
                        for l in range(i + 1, len(strings)):
                            if it.match(strings[l][j:k + 1]):
                                if ex.match(strings[l][j:k + 1]):
                                    count += 1
                            else:
                                break
    return count
