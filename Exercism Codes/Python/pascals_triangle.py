def rows(row_count):
    if row_count < 0:
        raise ValueError("number of rows is negative")

    return insert([], [1], 0, row_count)

def insert(result, prev_arr, row, row_count):
    if row == row_count:
        return result

    arr = []
    for i in range(row + 1):
        arr.append(check_bounds(prev_arr, i) + check_bounds(prev_arr, i - 1))
    result.append(arr)

    return insert(result, arr, row + 1, row_count)

def check_bounds(arr, idx):
    return arr[idx] if len(arr) > idx >= 0 else 0