def gamestate(board):
    x_count = get_player_count(board, "X")
    o_count = get_player_count(board, "O")
    if x_count - o_count > 1:
        raise ValueError("Wrong turn order: X went twice")
    if x_count - o_count < 0:
        raise ValueError("Wrong turn order: O started")
    if check_win(board, "X") and check_win(board, "O"):
        raise ValueError("Impossible board: game should have ended after the game was won")
    if check_win(board, "X") or check_win(board, "O"):
        return "win"
    if " " not in board[0] and " " not in board[1] and " " not in board[2]:
        return "draw"

    return "ongoing"

def check_win(board, p):
    states = [check_diagonal_win(board, p, -1), check_diagonal_win(board, p, 1)]
    for i in range(3):
        states.append(check_vertical_win(board, p, i))
        states.append(check_horizontal_win(board, p, i))

    return any(states)

def check_vertical_win(board, p, line):
    return board[0][line] == p and board[1][line] == p and board[2][line] == p

def check_horizontal_win(board, p, line):
    return board[line] == p * 3

def check_diagonal_win(board, p, direction):
    return board[0][1 + direction] == p and board[1][1] == p and board[2][1 - direction] == p

def get_player_count(board, p):
    count = 0
    for row in board:
        for cell in row:
            if cell == p:
                count += 1
    return count