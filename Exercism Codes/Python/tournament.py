def tally(rows):
    data = [s.split(";") for s in rows]
    players = []
    for d in data:
        for i in range(2):
            status = d[2] if i == 0 else reverse(d[2])
            try:
                player_idx = [p.name for p in players].index(d[i])
                players[player_idx].update(status)
            except ValueError:
                players.append(Player(d[i], status))

    player_data = sorted(players, key=lambda p: (-p.points, p.name))

    lines = ["Team                           | MP |  W |  D |  L |  P"]

    for p in player_data:
        lines.append(f"{p.name}{" " * (31 - len(p.name))}| {p.played:2d} | {p.win:2d} | {p.draw:2d} | {p.loss:2d} | {p.points:2d}")

    return lines


def reverse(status):
    return "draw" if status == "draw" else ("loss" if status == "win" else "win")


class Player:
    def __init__(self, name, value):
        self.points = 0
        self.played = 0
        self.name = name
        self.win = 0
        self.loss = 0
        self.draw = 0

        self.update(value)

    def update(self, value):
        if value == "win":
            self.win += 1
        if value == "loss":
            self.loss += 1
        if value == "draw":
            self.draw += 1

        self.played = self.win + self.loss + self.draw
        self.points = self.win * 3 + self.draw