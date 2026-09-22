class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __repr__(self):
        return f"Point({self.x}, {self.y})"


class WordSearch:
    def __init__(self, puzzle):
        self.puzzle = puzzle

    def search(self, word):
        ln = len(word)
        rl = len(self.puzzle)
        for i in range(rl):
            cl = len(self.puzzle[i])
            for j in range(cl):
                if word[0] == self.puzzle[i][j]:
                    if self.srch(word, ln <= i + 1, i, j, -1, 0):
                        return Point(j, i), Point(j, i - (ln - 1))
                    if self.srch(word, ln <= j + 1, i, j, 0, -1):
                        return Point(j, i), Point(j - (ln - 1), i)
                    if self.srch(word, ln <= rl - i, i, j, 1, 0):
                        return Point(j, i), Point(j, i + (ln - 1))
                    if self.srch(word, ln <= cl - j, i, j, 0, 1):
                        return Point(j, i), Point(j + (ln - 1), i)
                    if self.srch(word, ln <= i + 1 and ln <= j + 1, i, j, -1, -1):
                        return Point(j, i), Point(j - (ln - 1), i - (ln - 1))
                    if self.srch(word, ln <= i + 1 and ln <= cl - j, i, j, -1, 1):
                        return Point(j, i), Point(j + (ln - 1), i - (ln - 1))
                    if self.srch(word, ln <= rl - i and ln <= j + 1, i, j, 1, -1):
                        return Point(j, i), Point(j - (ln - 1), i + (ln - 1))
                    if self.srch(word, ln <= rl - i and ln <= cl - j, i, j, 1, 1):
                        return Point(j, i), Point(j + (ln - 1), i + (ln - 1))
        return None

    def srch(self, word, condition, x, y, x_offset, y_offset):
        if condition:
            count = 1
            for i in range(1, len(word)):
                if word[i] == self.puzzle[x + (i * x_offset)][y + (i * y_offset)]:
                    count += 1

            return count == len(word)
        return False
