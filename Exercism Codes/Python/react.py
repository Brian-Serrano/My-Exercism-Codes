class InputCell:
    def __init__(self, initial_value):
        self._value = initial_value
        self.observers = []

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, value):
        values = []

        for i in range(len(self.observers)):
            values.append((self.observers[i][0].value, i))

        self._value = value

        for i in range(len(self.observers)):
            if self.observers[i][0].value != values[i][0]:
                self.observers[i][1](self.observers[i][0].value)

class ComputeCell:
    def __init__(self, inputs, compute_function):
        self.inputs = inputs
        self.compute_function = compute_function

    @property
    def value(self):
        return self.compute_function([x.value for x in self.inputs])

    def add_callback(self, callback):
        self.add(callback, self.inputs)

    def add(self, callback, inputs):
        for inp in inputs:
            if isinstance(inp, InputCell):
                inp.observers.append((self, callback))
            else:
                self.add(callback, inp.inputs)

    def remove_callback(self, callback):
        self.remove(callback, self.inputs)

    def remove(self, callback, inputs):
        for inp in inputs:
            if isinstance(inp, InputCell):
                callbacks = [x[1] for x in inp.observers]
                if callback in callbacks:
                    inp.observers.pop(callbacks.index(callback))
            else:
                self.remove(callback, inp.inputs)