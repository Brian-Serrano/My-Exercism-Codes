import re


def encode(plain_text, a, b):
    if a % 2 == 0 or a % 13 == 0:
        raise ValueError("a and m must be coprime.")
    e = list(re.sub("\\W", "", plain_text.lower()))
    en = [ord(x) - ord("a") if 48 <= ord(x) <= 57 else ((a * (ord(x) - ord("a"))) + b) % 26 for x in e]
    enc = "".join(chr(x + ord("a")) for x in en)
    return " ".join(re.findall(r".{1,5}", enc))


def decode(ciphered_text, a, b):
    if a % 2 == 0 or a % 13 == 0:
        raise ValueError("a and m must be coprime.")
    d = list(re.sub("\\W", "", ciphered_text.lower()))
    de = [ord(x) if 48 <= ord(x) <= 57 else (mmi(a) * ((ord(x) - ord("a")) - b)) % 26 for x in d]
    return "".join(chr(x) if 48 <= x <= 57 else chr(((x + 26) % 26) + ord("a")) for x in de)


def mmi(a):
    for i in range(1, 26):
        if a * i % 26 == 1:
            return i
    return -1