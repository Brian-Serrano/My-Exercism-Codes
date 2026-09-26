def value_of_card(card, ace=0):
    if card in ['J', 'Q', 'K']:
        return 10
    elif card == 'A':
        return 1 + ace
    else:
        return int(card)


def higher_card(card_one, card_two):
    if value_of_card(card_one) > value_of_card(card_two):
        return card_one
    elif value_of_card(card_one) < value_of_card(card_two):
        return card_two
    else:
        return card_one, card_two


def value_of_ace(card_one, card_two):
    return 11 if value_of_card(card_one, 10) + value_of_card(card_two, 10) <= 10 else 1


def is_blackjack(card_one, card_two):
    return value_of_card(card_one, 10) + value_of_card(card_two, 10) == 21


def can_split_pairs(card_one, card_two):
    return value_of_card(card_one) == value_of_card(card_two)


def can_double_down(card_one, card_two):
    return value_of_card(card_one) + value_of_card(card_two) in [9, 10, 11]
