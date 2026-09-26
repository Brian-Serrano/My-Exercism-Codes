class CustomSet:
    def __init__(self, elements=[]):
        self.st = set(elements)

    def isempty(self):
        return len(self.st) == 0

    def __contains__(self, element):
        return element in self.st

    def issubset(self, other):
        return self.st.issubset(other.st)

    def isdisjoint(self, other):
        return self.st.isdisjoint(other.st)

    def __eq__(self, other):
        return self.st == other.st

    def add(self, element):
        self.st.add(element)

    def intersection(self, other):
        return CustomSet(list(self.st.intersection(other.st)))

    def __sub__(self, other):
        return CustomSet(list(self.st.difference(other.st)))

    def __add__(self, other):
        return CustomSet(list(self.st.union(other.st)))
