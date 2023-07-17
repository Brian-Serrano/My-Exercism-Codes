def is_isogram(string):
    string = string.replace(" ", "").replace("-", "").lower()
    return len(string) == len(set(string))
