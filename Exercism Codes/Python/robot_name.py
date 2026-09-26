import random


registeredName = set()


def generate_name():
    return random_name('A', 'Z', 2) + random_name('0', '9', 3)


def random_name(start, end, length):
    return ''.join(chr(random.randint(ord(start), ord(end))) for x in range(length))


class Robot:
    def __init__(self):
        self.name = None
        self.reset()
    
    def reset(self):
        while (name := generate_name()) in registeredName:
            continue

        registeredName.add(name)
        self.name = name




