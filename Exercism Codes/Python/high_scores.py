class HighScores:
    def __init__(self, scores):
        self.scores = scores

    def latest(self):
        return self.scores[-1]

    def personal_best(self):
        return sorted(self.scores, reverse=True)[0]

    def personal_top_three(self):
        return sorted(self.scores, reverse=True)[:3]