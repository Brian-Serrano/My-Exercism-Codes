class Matrix:
    def __init__(self, matrix_string):
        self.matrix = [[int(cell) for cell in row.split(" ")] for row in matrix_string.split("\n")]

    def row(self, index):
        return self.matrix[index - 1]

    def column(self, index):
        return [x[index - 1] for x in self.matrix]
