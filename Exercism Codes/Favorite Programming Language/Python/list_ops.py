def append(list1, list2):
    return list1 + list2


def concat(lists):
    result = []
    for lst in lists:
        result += lst

    return result


def filter(function, list):
    return [x for x in list if function(x)]


def length(list):
    return len(list)


def map(function, list):
    return [function(x) for x in list]


def foldl(function, list, initial):
    for item in list:
        initial = function(initial, item)

    return initial


def foldr(function, list, initial):
    for item in reverse(list):
        initial = function(initial, item)

    return initial


def reverse(list):
    return list[::-1]