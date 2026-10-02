allergen = {
    "eggs": 1,
    "peanuts": 2,
    "shellfish": 4,
    "strawberries": 8,
    "tomatoes": 16,
    "chocolate": 32,
    "pollen": 64,
    "cats": 128
}

class Allergies:

    def __init__(self, score):
        self.score = score % 256

    def allergic_to(self, item):
        return item in self.lst

    @property
    def lst(self):
        allergens = []
        allergen_list = list(allergen.keys())
        while self.score > 0:
            for i in range(len(allergen_list) - 1, -1, -1):
                if self.score >= allergen[allergen_list[i]]:
                    allergens.append(allergen_list[i])
                    self.score -= allergen[allergen_list[i]]
                    break
        return allergens