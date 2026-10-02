WHITE = 0
BLACK = 1
NONE = 2

class Board:
    def __init__(self, board):
        self.board = board

    def territory(self, x, y):
        territory = self.get_territory(x, y)
        players_surrounding = []

        for p in territory:
            offsets = [
                (p[0] + 1, p[1]),
                (p[0] - 1, p[1]),
                (p[0], p[1] + 1),
                (p[0], p[1] - 1)
            ]
            for o in offsets:
                if self.check_bounds(o) and (self.board[o[1]][o[0]] == "B" or self.board[o[1]][o[0]] == "W"):
                    players_surrounding.append(self.board[o[1]][o[0]])

        return BLACK if self.contains(players_surrounding, "W", "B") else \
            (WHITE if self.contains(players_surrounding, "B", "W") else NONE), territory

    def territories(self):
        territories = {WHITE: set(), BLACK: set(), NONE: set()}
        for i in range(len(self.board)):
            for j in range(len(self.board[i])):
                t = self.territory(j, i)
                territories[t[0]].update(t[1])

        return territories

    def get_territory(self, x, y):
        if not self.check_bounds((x, y)):
            raise ValueError("Invalid coordinate")

        return self.get_adjacent_points(set(), (x, y)) if self.board[y][x] == " " else set()

    def get_adjacent_points(self, points, target):
        offsets = [
            (target[0] + 1, target[1]),
            (target[0] - 1, target[1]),
            (target[0], target[1] + 1),
            (target[0], target[1] - 1)
        ]
        points.add(target)
        for p in offsets:
            if self.check_bounds(p) and self.board[p[1]][p[0]] == " " and p not in points:
                self.get_adjacent_points(points, p)

        return points

    def check_bounds(self, point):
        return 0 <= point[0] < len(self.board[0]) and 0 <= point[1] < len(self.board)

    def contains(self, p, nc, c):
        return nc not in p and c in p