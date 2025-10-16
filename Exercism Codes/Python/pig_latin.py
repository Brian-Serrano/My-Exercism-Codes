def translate(text):
    result = []

    for c in text.split(" "):
        sl = 0
        while True:
            is_vowel = c[sl] in "aeiou"
            rule_1 = c[sl:sl + 2] == "yt" or c[sl:sl + 2] == "xr"
            rule_4 = c[sl - 1:sl] not in "aeiou" and c[sl] == "y"
            if is_vowel or rule_1 or rule_4:
                break

            if c[sl:sl + 2] == "qu":
                sl += 1

            sl += 1

        result.append(c[sl:] + c[:sl] + "ay")

    return " ".join(result)
