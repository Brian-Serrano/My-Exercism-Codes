class Garden:
    def __init__(self, diagram, students=None):
        self.garden = diagram.split("\n")
        self.students = sorted(students) if students else None

    def plants(self, student):
        plants = []
        for g in self.garden:
            ch = 2 * (self.students.index(student) if self.students else ord(student[0]) - 65)
            plants.append(self.get_plant(g[ch]))
            plants.append(self.get_plant(g[ch + 1]))
        return plants

    @staticmethod
    def get_plant(plant_code):
        if plant_code == "G":
            return "Grass"
        if plant_code == "C":
            return "Clover"
        if plant_code == "R":
            return "Radishes"
        if plant_code == "V":
            return "Violets"

        return ""