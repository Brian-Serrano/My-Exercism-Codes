def flatten(iterable):
    lst = []
    for it in iterable:
        if it is not None:
            if isinstance(it, list):
                lst += flatten(it)
            else:
                lst.append(it)

    return lst
