def score(word):
    word_arr = word.lower()
    points = 0
    for letter in word_arr:
        if letter in "aeioulnrst":
            points += 1
        if letter in "dg":
            points += 2
        if letter in "bcmp":
            points += 3
        if letter in "fhvwy":
            points += 4
        if letter in "k":
            points += 5
        if letter in "jx":
            points += 8
        if letter in "qz":
            points += 10

    return points