import random
import string


class Cipher:
    def __init__(self, key=None):
        if key is None:
            key = "".join(random.choice(string.ascii_lowercase) for _ in range(100))

        self.key = key

    def encode(self, text):
        return "".join(chr(97 + ((ord(x) - 97) + (ord(self.key[idx % len(self.key)]) - 97)) % 26) for idx, x in enumerate(text))

    def decode(self, text):
        return "".join(chr(97 + ((ord(x) - 97) - (ord(self.key[idx % len(self.key)]) - 97)) % 26) for idx, x in enumerate(text))
