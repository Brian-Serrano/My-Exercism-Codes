import bisect


class School:
    def __init__(self):
        self.students = dict()
        self.add = []

    def add_student(self, name, grade):
        if name not in [y for x in self.students.values() for y in x]:
            if grade not in self.students:
                self.students[grade] = []

            bisect.insort(self.students[grade], name)
            self.add.append(True)
        else:
            self.add.append(False)

    def roster(self):
        return [y for x in dict(sorted(self.students.items())).values() for y in x]

    def grade(self, grade_number):
        return self.students[grade_number] if grade_number in self.students else []

    def added(self):
        return self.add