def abbreviate(words):
    lst = words.replace("-", " ").replace("_", " ").split()
    return ''.join([x[0] for x in lst]).upper()

