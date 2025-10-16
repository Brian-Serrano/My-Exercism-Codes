def encode(plain_text):
    result = []
    for c in plain_text:
        if c.isalnum():
            result.append(transform(c))

            if (len(result) + 1) % 6 == 0:
                result.append(" ")

    return "".join(result).strip()


def decode(ciphered_text):
    return "".join(transform(x) for x in ciphered_text if x.isalnum())

def transform(x):
    return chr((26 - (ord(x.lower()) - 97) - 1) + 97) if x.isalpha() else x