import re


def count_words(sentence: str):
    arr = re.split("[^a-zA-Z0-9']+", sentence.lower().strip())
    result = dict()
    for word in arr:
        w = re.sub("^'{1,2}|'{1,2}$", "", word)
        if w:
            if w in result:
                result[w] += 1
            else:
                result[w] = 1
    return result