import itertools


def combinations(target, size, exclude):
    result = []
    for i in itertools.combinations(range(1, 10), size):
        if sum(i) == target and not any(x in i for x in exclude):
            result.append(list(i))
    return result