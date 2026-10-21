def tick(matrix):
    el_to_change = []

    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            k = 0
            if i + 1 < len(matrix) and matrix[i + 1][j] == 1:
                k += 1
            if i - 1 >= 0 and matrix[i - 1][j] == 1:
                k += 1
            if j + 1 < len(matrix[i]) and matrix[i][j + 1] == 1:
                k += 1
            if j - 1 >= 0 and matrix[i][j - 1] == 1:
                k += 1
            if i + 1 < len(matrix) and j + 1 < len(matrix[i]) and matrix[i + 1][j + 1] == 1:
                k += 1
            if i + 1 < len(matrix) and j - 1 >= 0 and matrix[i + 1][j - 1] == 1:
                k += 1
            if i - 1 >= 0 and j + 1 < len(matrix[i]) and matrix[i - 1][j + 1] == 1:
                k += 1
            if i - 1 >= 0 and j - 1 >= 0 and matrix[i - 1][j - 1] == 1:
                k += 1

            if matrix[i][j] == 1:
                if k < 2 or k >= 4:
                    el_to_change.append((i, j, 0))
            else:
                if k == 3:
                    el_to_change.append((i, j, 1))

    for e in el_to_change:
        matrix[e[0]][e[1]] = e[2]

    return matrix