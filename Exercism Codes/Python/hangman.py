# Game status categories
# Change the values as you see fit
STATUS_WIN = 'win'
STATUS_LOSE = 'lose'
STATUS_ONGOING = 'ongoing'


class Hangman:
    def __init__(self, word):
        self.remaining_guesses = 9
        self.status = STATUS_ONGOING
        self.word = word
        self.mask = list("_" * len(word))
        self.letters = []

    def guess(self, char):
        if self.status != STATUS_ONGOING:
            raise ValueError("The game has already ended.")

        guessed_correct = False

        for i in range(len(self.word)):
            if self.word[i] == char and char not in self.letters:
                self.mask[i] = char
                guessed_correct = True

        if "_" not in self.mask:
            self.status = STATUS_WIN

        if not guessed_correct:
            self.remaining_guesses -= 1

            if self.remaining_guesses < 0:
                self.status = STATUS_LOSE
        else:
            self.letters.append(char)

    def get_masked_word(self):
        return "".join(self.mask)

    def get_status(self):
        return self.status
