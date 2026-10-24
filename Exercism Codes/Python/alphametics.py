import re

def solve(puzzle):
    letters = list(set(x for x in puzzle if x.isalpha()))
    words = re.split(r"\s?\+\s?|\s==\s", puzzle)
    first_letters = [x[0] for x in words]
    result = backtrack({}, letters, words, first_letters)
    return result if result else None

def backtrack(mapping, letters, words, first_letters):
    if len(letters) == 0:
        return mapping

    letter = letters.pop(0)

    for i in range(10):
        if i in mapping.values():
            continue
        if i == 0 and letter in first_letters:
            continue

        mapping[letter] = i
        new_mapping = backtrack(mapping, letters, words, first_letters)

        if len(new_mapping) > 0 and is_valid(mapping, words):
            return new_mapping

        mapping.pop(letter, None)

    letters.insert(0, letter)
    return {}

def is_valid(mapping, words):
    numbers = [int("".join(str(mapping[c]) for c in w)) for w in words]
    return sum(numbers[:-1]) == numbers[-1]