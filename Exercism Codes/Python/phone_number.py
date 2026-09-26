import re

import unicodedata


class PhoneNumber:
    def __init__(self, number):
        n = re.sub("[\\s\\-()\\.+]", "", number)
        if len(n) == 11:
            if n[0] != "1":
                raise ValueError("11 digits must start with 1")
            else:
                n = n[1:]
        elif len(n) > 11:
            raise ValueError("must not be greater than 11 digits")
        elif len(n) < 10:
            raise ValueError("must not be fewer than 10 digits")

        if any(unicodedata.category(c).startswith("P") for c in n):
            raise ValueError("punctuations not permitted")
        if any(c.isalpha() for c in n):
            raise ValueError("letters not permitted")
        if n[0] == "0":
            raise ValueError("area code cannot start with zero")
        if n[0] == "1":
            raise ValueError("area code cannot start with one")
        if n[3] == "0":
            raise ValueError("exchange code cannot start with zero")
        if n[3] == "1":
            raise ValueError("exchange code cannot start with one")

        self.number = n
        self.area_code = n[:-7]

    def pretty(self):
        return f"({self.number[:-7]})-{self.number[-7:-4]}-{self.number[-4:]}"
