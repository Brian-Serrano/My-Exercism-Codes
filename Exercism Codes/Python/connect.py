class ConnectGame:
    def __init__(self, board):
        rows = [x.strip().split(' ') for x in board.split('\n')]

        def check(row, col, visited, l):
            if col == len(rows[0]) if l == 'X' else row == len(rows):
                return True

            if not (0 <= row < len(rows)) or not (0 <= col < len(rows[0])) or visited[row][col]:
                return False

            visited[row][col] = True

            if rows[row][col] == l:
                return (check(row + 1, col, visited, l) or
                        check(row - 1, col, visited, l) or
                        check(row, col + 1, visited, l) or
                        check(row, col - 1, visited, l) or
                        check(row - 1, col + 1, visited, l) or
                        check(row + 1, col - 1, visited, l))

            return False

        x_wins = False
        o_wins = False

        for x in range(len(rows)):
            if rows[x][0] == 'X':
                wins = check(x, 0, [[*map(lambda x: False, y)] for y in rows], 'X')
                if wins:
                    x_wins = True
                    break

        for o in range(len(rows[0])):
            if rows[0][o] == 'O':
                wins = check(0, o, [[*map(lambda x: False, y)] for y in rows], 'O')
                if wins:
                    o_wins = True
                    break

        if o_wins:
            self.winner = 'O'
        elif x_wins:
            self.winner = 'X'
        else:
            self.winner = ''

    def get_winner(self):
        return self.winner