class Luhn:
    def __init__(self, card_num):
        self.card_num = card_num.replace(" ", "")

    def valid(self):
        if not self.card_num.isdigit() or len(self.card_num) < 2:
            return False

        digits = [int(x) for x in self.card_num]
        i = 0 if len(digits) % 2 == 0 else 1
        while i < len(digits):
            digits[i] *= 2
            if digits[i] > 9:
                digits[i] -= 9
            i += 2

        return sum(digits) % 10 == 0