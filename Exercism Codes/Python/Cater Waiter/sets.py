from functools import reduce

from sets_categories_data import (VEGAN,
                                  VEGETARIAN,
                                  KETO,
                                  PALEO,
                                  OMNIVORE,
                                  ALCOHOLS,
                                  SPECIAL_INGREDIENTS)


def clean_ingredients(dish_name, dish_ingredients):
    return dish_name, set(dish_ingredients)


def check_drinks(drink_name, drink_ingredients):
    return drink_name + ' Cocktail' if len(set(drink_ingredients) & ALCOHOLS) != 0 else drink_name + ' Mocktail'


def categorize_dish(dish_name, dish_ingredients):
    if dish_ingredients <= VEGAN:
        return dish_name + ': VEGAN'
    if dish_ingredients <= VEGETARIAN:
        return dish_name + ': VEGETARIAN'
    if dish_ingredients <= KETO:
        return dish_name + ': KETO'
    if dish_ingredients <= PALEO:
        return dish_name + ': PALEO'
    if dish_ingredients <= OMNIVORE:
        return dish_name + ': OMNIVORE'
    return dish_name


def tag_special_ingredients(dish):
    return dish[0], SPECIAL_INGREDIENTS.intersection(dish[1])


def compile_ingredients(dishes):
    return reduce(lambda a, n: a | n, dishes, set())


def separate_appetizers(dishes, appetizers):
    return list(set(dishes) - set(appetizers))


def singleton_ingredients(dishes, intersection):
    return reduce(lambda a, n: a | (n - intersection), dishes, set())
