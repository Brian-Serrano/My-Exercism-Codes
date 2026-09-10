import re


def decode(string):
    matches = re.findall(r"(\d*[A-Z a-z])", string)
    output = ""

    for match in matches:
        letter = match[-1:]
        number = 1 if not match[:-1] else int(match[:-1])
        output += letter * number

    return output


def encode(string):
    count = 1
    output = ""
    for i in range(1, len(string) + 1):
        if i < len(string) and string[i] == string[i - 1]:
            count += 1
        else:
            output += ("" if count < 2 else str(count)) + string[i - 1]
            count = 1

    return output