class LinkedList:
    def __init__(self):
        self.list = []

    def __len__(self):
        return len(self.list)

    def push(self, value):
        self.list.append(value)

    def unshift(self, value):
        self.list.insert(0, value)

    def pop(self):
        if len(self.list) == 0:
            raise IndexError("List is empty")

        return self.list.pop()

    def shift(self):
        if len(self.list) == 0:
            raise IndexError("List is empty")

        return self.list.pop(0)

    def delete(self, value):
        if value not in self.list:
            raise ValueError("Value not found")

        self.list.remove(value)