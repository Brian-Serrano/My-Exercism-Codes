def saddle_points(matrix):
    if len(matrix) == 0:
        return {}
    try:
        maximum = [max(x) for x in matrix]
        minimum = [min(y[x] for y in matrix) for x in range(len(matrix[0]))]
        return flatten(
            [[{"row": x + 1, "column": y + 1} for y in range(len(matrix[x])) if maximum[x] <= matrix[x][y] <= minimum[y]]
             for x in range(len(matrix))])
    except:
        raise ValueError("irregular matrix")


def flatten(lst):
    return [i for item in lst for i in item]
