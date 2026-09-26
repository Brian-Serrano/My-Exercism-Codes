def transform(legacy_data):
    lst = {}
    for k, v in legacy_data.items():
        for i in v:
            lst[i.lower()] = k
    return lst
