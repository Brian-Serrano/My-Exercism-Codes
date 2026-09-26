class BufferFullException(BufferError):
    """Exception raised when CircularBuffer is full.

    message: explanation of the error.

    """
    def __init__(self, message):
        pass


class BufferEmptyException(BufferError):
    """Exception raised when CircularBuffer is empty.

    message: explanation of the error.

    """
    def __init__(self, message):
        pass


class CircularBuffer:
    def __init__(self, capacity):
        self.capacity = capacity
        self.elements = []

    def read(self):
        if len(self.elements) == 0:
            raise BufferEmptyException("Circular buffer is empty")

        return self.elements.pop(0)

    def write(self, data):
        if len(self.elements) == self.capacity:
            raise BufferFullException("Circular buffer is full")

        self.elements.append(data)

    def overwrite(self, data):
        if len(self.elements) == self.capacity:
            self.clear()

        self.elements.append(data)

    def clear(self):
        if len(self.elements) > 0:
            self.elements.pop(0)
