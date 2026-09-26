class BowlingGame:
    def __init__(self):
        self.scr = 0
        self.num_of_throws = 0
        self.num_of_game = 0
        self.game_score = 0
        self.bonus = 0

    def roll(self, pins):
        if pins < 0:
            raise Exception("Negative roll is invalid")
        if self.num_of_game >= 10:
            if self.bonus == 0:
                raise Exception("Cannot roll after game is over")
            else:
                self.scr += pins * 2 if self.bonus > 2 else pins
                self.bonus -= 2 if self.bonus > 2 else 1
                self.game_score += pins
                if self.game_score > 10:
                    raise Exception("Pin count exceeds pins on the lane")
                if self.game_score == 10:
                    self.game_score = 0
        else:
            self.scr += pins * ((3 if self.bonus > 2 else 2) if self.bonus > 0 else 1)
            self.bonus -= 0 if self.bonus == 0 else (2 if self.bonus > 2 else 1)
            self.game_score += pins
            self.num_of_throws += 1
            if self.game_score > 10:
                raise Exception("Pin count exceeds pins on the lane")
            if self.num_of_throws == 1 and self.game_score == 10:
                self.bonus += 2
                self.num_of_game += 1
                self.num_of_throws = 0
                self.game_score = 0
            if self.num_of_throws == 2:
                self.bonus += 1 if self.game_score == 10 else 0
                self.num_of_game += 1
                self.num_of_throws = 0
                self.game_score = 0

    def score(self):
        if self.num_of_game == 10 and self.bonus == 0:
            return self.scr
        else:
            raise Exception("Score cannot be taken until the end of the game")
