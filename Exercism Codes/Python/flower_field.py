def annotate(garden):
    if len(set(len(x) for x in garden)) > 1:
        raise ValueError("The board is invalid with current input.")
    if any(any(y not in ["*", " "] for y in list(x)) for x in garden):
        raise ValueError("The board is invalid with current input.")

    matrix = [list(x) for x in garden]

    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            if matrix[i][j] != "*":
                k = 0
                if i + 1 < len(matrix) and matrix[i + 1][j] == "*":
                    k += 1
                if i - 1 >= 0 and matrix[i - 1][j] == "*":
                    k += 1
                if j + 1 < len(matrix[i]) and matrix[i][j + 1] == "*":
                    k += 1
                if j - 1 >= 0 and matrix[i][j - 1] == "*":
                    k += 1
                if i + 1 < len(matrix) and j + 1 < len(matrix[i]) and matrix[i + 1][j + 1] == "*":
                    k += 1
                if i + 1 < len(matrix) and j - 1 >= 0 and matrix[i + 1][j - 1] == "*":
                    k += 1
                if i - 1 >= 0 and j + 1 < len(matrix[i]) and matrix[i - 1][j + 1] == "*":
                    k += 1
                if i - 1 >= 0 and j - 1 >= 0 and matrix[i - 1][j - 1] == "*":
                    k += 1

                if k > 0:
                    matrix[i][j] = str(k)

    return ["".join(x) for x in matrix]
