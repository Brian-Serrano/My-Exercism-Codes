def transpose(text):
    st = pad_string_array(text.split("\n"))
    stra = [""] * len(st[0])
    for s in st:
        for j in range(len(s)):
            stra[j] += s[j]
    return "\n".join(stra)

def pad_string_array(original):
    for i in range(len(original) - 1, 0, -1):
        if len(original[i]) > len(original[i - 1]):
            rep = len(original[i]) - len(original[i - 1])
            original[i - 1] += " " * rep

    return original