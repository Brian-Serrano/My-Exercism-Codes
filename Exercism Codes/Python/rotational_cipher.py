def rotate(text, key):
    return "".join(rot_letter(x, key) for x in text)

def rot_letter(x: str, key):
    return chr((((ord(x) - is_upper(x)) + key) % 26) + is_upper(x)) if x.isalpha() else x

def is_upper(x):
    return 65 if x.isupper() else 97