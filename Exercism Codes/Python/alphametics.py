import itertools
import re

def solve(puzzle: str):
    letters_set = set()
    beginning_letters = set()
    for idx, letter in enumerate(puzzle):
        if letter.isalpha():
            if puzzle[idx - 1] in [' ', '+'] or idx == 0:
                beginning_letters.add(letter)
            letters_set.add(letter)

    print(beginning_letters)

    for perm in itertools.permutations(range(0, 10), len(letters_set)):
        mapping = {}
        skip_from_outer_loop = False

        for idx, letter in enumerate(letters_set):
            if letter in beginning_letters and perm[idx] == 0:
                skip_from_outer_loop = True
                break

            mapping[letter] = perm[idx]

        if not skip_from_outer_loop:
            mapped = "".join(str(mapping[x]) if x in mapping.keys() else x for x in puzzle)
            numbers = re.split(r"\s?\+\s?|\s==\s", mapped)
            left_side = numbers[:len(numbers) - 1]
            right_side = int(numbers[len(numbers) - 1])
            if sum(int(x) for x in left_side) == right_side:
                return mapping


    return None