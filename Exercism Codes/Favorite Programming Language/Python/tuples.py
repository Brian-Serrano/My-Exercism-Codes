def get_coordinate(record):
    return record[1]


def convert_coordinate(coordinate):
    return tuple(coordinate)


def compare_records(azara_record, rui_record):
    return convert_coordinate(get_coordinate(azara_record)) == get_coordinate(rui_record)


def create_record(azara_record, rui_record):
    return azara_record + rui_record if compare_records(azara_record, rui_record) else 'not a match'


def clean_up(combined_record_group):
    new_list = []
    for i in combined_record_group:
        new_list.append("('{}', '{}', ('{}', '{}'), '{}')\n".format(i[0], i[2], i[3][0], i[3][1], i[4]))
    return ''.join(new_list)
