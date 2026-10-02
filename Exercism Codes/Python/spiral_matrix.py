def spiral_matrix(size):
    matrix = [[0] * size for _ in range(size)]
    ver_idx, hor_idx, direction = 0, 0, 0

    if size * size > 0:
        matrix[0][0] = 1

    for i in range(1, size * size):
        if direction == 0:
            if len(matrix[0]) > hor_idx + 1 and matrix[ver_idx][hor_idx + 1] == 0:
                hor_idx += 1
            else:
                ver_idx += 1
                direction = 1
        elif direction == 1:
            if len(matrix) > ver_idx + 1 and matrix[ver_idx + 1][hor_idx] == 0:
                ver_idx += 1
            else:
                hor_idx -= 1
                direction = 2
        elif direction == 2:
            if hor_idx - 1 >= 0 and matrix[ver_idx][hor_idx - 1] == 0:
                hor_idx -= 1
            else:
                ver_idx -= 1
                direction = 3
        elif direction == 3:
            if ver_idx - 1 >= 0 and matrix[ver_idx - 1][hor_idx] == 0:
                ver_idx -= 1
            else:
                hor_idx += 1
                direction = 0

        matrix[ver_idx][hor_idx] = i + 1

    return matrix