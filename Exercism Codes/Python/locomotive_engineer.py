def get_list_of_wagons(*args):
    return [*args]


def fix_list_of_wagons(each_wagons_id, missing_wagons):
    [[a, b, c, *last], d] = [each_wagons_id, missing_wagons]
    return [c, *d, *last, a, b]


def add_missing_stops(dicts, **kwargs):
    return {**dicts, 'stops': [*kwargs.values()]}


def extend_route_information(route, more_route_information):
    return {**route, **more_route_information}


def fix_wagon_depot(wagons_rows):
    return [*map(lambda a: list(a), [*zip(*wagons_rows)])]
