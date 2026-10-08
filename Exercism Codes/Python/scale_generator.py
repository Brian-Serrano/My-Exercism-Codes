class Scale:
    def __init__(self, tonic: str):
        self.tonic = tonic

        if self.tonic in ["C", "G", "D", "A", "E", "B", "F#", "a", "e", "b", "f#", "c#", "g#", "d#"]:
            self.notes = ["A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"]
        else:
            self.notes = ["A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"]

        self.intrval = "mMA"

    def chromatic(self):
        first = self.notes[self.notes.index(self.tonic.title()):]
        last = self.notes[:self.notes.index(self.tonic.title())]
        return first + last

    def interval(self, intervals):
        current_idx = self.notes.index(self.tonic.title())
        result = []
        for interval in intervals:
            result.append(self.notes[current_idx])
            current_idx = (current_idx + (self.intrval.index(interval) + 1)) % 12
        result.append(result[0])
        return result
