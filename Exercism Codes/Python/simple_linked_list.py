class EmptyListException(Exception):
    pass


class Node:
    def __init__(self, value):
        self.val = value
        self.nxt = None

    def value(self):
        return self.val

    def next(self):
        return self.nxt

    def set(self, value):
        self.nxt = value

class LinkedList:
    def __init__(self, values=None):
        self.h = None

        if values:
            for value in values:
                self.push(value)

    def __iter__(self):
        node = self.h
        while node:
            yield node.value()
            node = node.next()

    def __len__(self):
        if not self.h:
            return 0
        else:
            count = 1
            node = self.h
            while node.next():
                count += 1
                node = node.next()

            return count

    def head(self):
        if not self.h:
            raise EmptyListException("The list is empty.")

        return self.h

    def push(self, value):
        node = self.h
        self.h = Node(value)
        self.h.set(node)

    def pop(self):
        if not self.h:
            raise EmptyListException("The list is empty.")
        else:
            value = self.h.value()
            self.h = self.h.next()
            return value

    def reversed(self):
        return list(self)[::-1]
