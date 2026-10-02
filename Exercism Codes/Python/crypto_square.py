import math
import re


def cipher_text(plain_text):
    normalized = re.sub("\\W", "", plain_text.lower())
    ln = len(normalized)
    c = math.ceil(math.sqrt(ln))
    r = math.floor(math.sqrt(ln))
    if c * r > ln:
        normalized += " " * (r - (ln % r))
    if c * r < ln:
        normalized += " " * (c - (ln % c))
        r += 1
    rect = re.findall(".{" + str(c) + "}", normalized)
    return " ".join("".join(str(lst[idx]) for lst in rect) for idx in range(0, len(rect[0])))