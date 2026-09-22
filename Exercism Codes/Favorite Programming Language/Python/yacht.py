# Score categories.
# Change the values as you see fit.
YACHT = lambda x: 50 if len(set(x)) == 1 else 0
ONES = lambda x: sum(d if d == 1 else 0 for d in x)
TWOS = lambda x: sum(d if d == 2 else 0 for d in x)
THREES = lambda x: sum(d if d == 3 else 0 for d in x)
FOURS = lambda x: sum(d if d == 4 else 0 for d in x)
FIVES = lambda x: sum(d if d == 5 else 0 for d in x)
SIXES = lambda x: sum(d if d == 6 else 0 for d in x)
FULL_HOUSE = lambda x: sum(x) if {2, 3} == set(x.count(d) for d in set(x)) else 0
FOUR_OF_A_KIND = lambda x: sum(d for d in set(x) if x.count(d) >= 4) * 4
LITTLE_STRAIGHT = lambda x: 30 if {1, 2, 3, 4, 5} == set(x) else 0
BIG_STRAIGHT = lambda x: 30 if {2, 3, 4, 5, 6} == set(x) else 0
CHOICE = lambda x: sum(x)


def score(dice, category):
    return category(dice)
