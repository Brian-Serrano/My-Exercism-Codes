import math
import re


def largest_product(series, size):
    if re.search(".*[a-zA-Z].*", series):
        raise ValueError("digits input must only contain digits")
    if size > len(series):
        raise ValueError("span must not exceed string length")
    if size < 0:
        raise ValueError("span must not be negative")

    subnumbers = []
    i = 0
    while i + size <= len(series):
        subnumbers.append(series[i:i+size])
        i += 1

    return max(calculate_digits(x) for x in subnumbers)

def calculate_digits(subnumber):
    return math.prod(int(x) for x in list(subnumber))