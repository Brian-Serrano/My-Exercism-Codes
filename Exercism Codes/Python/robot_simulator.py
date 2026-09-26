# Globals for the directions
# Change the values as you see fit
EAST = "EAST"
NORTH = "NORTH"
WEST = "WEST"
SOUTH = "SOUTH"


class Robot:
    def __init__(self, direction=NORTH, x_pos=0, y_pos=0):
        self.coordinates = (x_pos, y_pos)
        self.direction = direction

    def move(self, instruction):
        for i in range(len(instruction)):
            if instruction[i] == "L":
                self.turn_left()
            elif instruction[i] == "R":
                self.turn_right()
            elif instruction[i] == "A":
                self.advance()

    def turn_left(self):
        if self.direction == NORTH:
            self.direction = WEST
        elif self.direction == EAST:
            self.direction = NORTH
        elif self.direction == SOUTH:
            self.direction = EAST
        elif self.direction == WEST:
            self.direction = SOUTH

    def turn_right(self):
        if self.direction == NORTH:
            self.direction = EAST
        elif self.direction == EAST:
            self.direction = SOUTH
        elif self.direction == SOUTH:
            self.direction = WEST
        elif self.direction == WEST:
            self.direction = NORTH

    def advance(self):
        if self.direction == NORTH:
            self.coordinates = (self.coordinates[0], self.coordinates[1] + 1)
        elif self.direction == EAST:
            self.coordinates = (self.coordinates[0] + 1, self.coordinates[1])
        elif self.direction == SOUTH:
            self.coordinates = (self.coordinates[0], self.coordinates[1] - 1)
        elif self.direction == WEST:
            self.coordinates = (self.coordinates[0] - 1, self.coordinates[1])