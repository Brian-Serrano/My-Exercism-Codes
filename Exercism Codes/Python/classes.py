"""Solution to Ellen's Alien Game exercise."""


class Alien:
    total_aliens_created = 0

    def __init__(self, x_coordinate, y_coordinate):
        self.x_coordinate = x_coordinate
        self.y_coordinate = y_coordinate
        self.health = 3
        Alien.total_aliens_created += 1

    def hit(self):
        self.health -= 1

    def is_alive(self):
        return self.health > 0

    def teleport(self, x_coordinate, y_coordinate):
        self.x_coordinate = x_coordinate
        self.y_coordinate = y_coordinate

    def collision_detection(self, other):
        pass


def new_aliens_collection(start_positions):
    return [Alien(x, y) for x, y in start_positions]

