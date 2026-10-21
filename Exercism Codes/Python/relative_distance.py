class RelativeDistance:
    def __init__(self, family_tree):
        self.family_tree = [[k, *v] for k, v in family_tree.items()]

    def degree_of_separation(self, person_a, person_b):
        if all(person_a not in p for p in self.family_tree):
            raise ValueError("Person A not in family tree.")
        if all(person_b not in p for p in self.family_tree):
            raise ValueError("Person B not in family tree.")

        dct = {"value": None}

        def find(person, visited):
            if person == person_b:
                value = dct["value"] if dct["value"] is not None else len(visited) - 1
                dct["value"] = min(value, len(visited) - 1)

            for node in self.family_tree:
                if person in node:
                    for n in node:
                        if person != n and n not in visited:
                            find(n, visited + [n])

        find(person_a, [person_a])

        if dct["value"] is None:
            raise ValueError("No connection between person A and person B.")

        return dct["value"]
