rank_values = {
    "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8,
    "9": 9, "10": 10, "J": 11, "Q": 12, "K": 13, "A": 14
}

class Hand:
    def __init__(self, hand, category, ranks):
        self.hand = hand
        self.category = category
        self.ranks = ranks

def best_hands(hands):
    hand_info = []

    for s in hands:
        straight_flush = is_straight_flush(s)
        square = is_square(s)
        full_house = is_full_house(s)
        flush = is_flush(s)
        straight = is_straight(s)
        three_of_kind = is_three_of_kind(s)
        two_pair = is_two_pair(s)
        one_pair = is_one_pair(s)

        if straight_flush[1]:
            hand_info.append(Hand(s, 9, straight_flush[0]))
        elif square[1]:
            hand_info.append(Hand(s, 8, square[0]))
        elif full_house[1]:
            hand_info.append(Hand(s, 7, full_house[0]))
        elif flush[1]:
            hand_info.append(Hand(s, 6, flush[0]))
        elif straight[1]:
            hand_info.append(Hand(s, 5, straight[0]))
        elif three_of_kind[1]:
            hand_info.append(Hand(s, 4, three_of_kind[0]))
        elif two_pair[1]:
            hand_info.append(Hand(s, 3, two_pair[0]))
        elif one_pair[1]:
            hand_info.append(Hand(s, 2, one_pair[0]))
        else:
            hand_info.append(Hand(s, 1, get_rank(s)))

    maximum = hand_info[0].category
    for hand in hand_info:
        if hand.category > maximum:
            maximum = hand.category

    max_hands = [h for h in hand_info if h.category == maximum]
    if len(max_hands) > 1:
        hand = []
        ranks = []
        for max_hand in max_hands:
            hand.append(max_hand.hand)
            ranks.append(max_hand.ranks)
        return highest(hand, ranks)

    return [h.hand for h in max_hands]

def is_straight_flush(hand):
    rank = get_rank(hand)
    suit = get_suit(hand)
    check_ace(rank)
    return [rank[-1]], len(set(suit)) == 1 and all(rank[n] + 1 == rank[n - 1] for n in range(1, 5))

def is_square(hand):
    rank = get_rank(hand)
    first = rank[0]
    last = rank[-1]
    num1 = rank.count(first)
    num2 = rank.count(last)
    return ([first if num1 == 4 else last, last if num1 == 4 else first],
            num1 == 4 or num2 == 4)

def is_full_house(hand):
    rank = get_rank(hand)
    first = rank[0]
    last = rank[-1]
    num1 = rank.count(first)
    num2 = rank.count(last)
    return ([first if num1 == 3 else last, last if num1 == 3 else first],
            {2, 3} == {num1, num2})

def is_flush(hand):
    suit = get_suit(hand)
    return get_rank(hand), len(set(suit)) == 1

def is_straight(hand):
    rank = get_rank(hand)
    check_ace(rank)
    return [rank[-1]], all(rank[n] + 1 == rank[n - 1] for n in range(1, 5))

def is_three_of_kind(hand):
    rank = get_rank(hand)
    middle = rank[len(rank) // 2]
    num1 = rank.count(middle)
    return [middle, *[c for c in rank if rank.count(c) == 1]], num1 == 3

def is_two_pair(hand):
    rank = get_rank(hand)
    second = rank[1]
    fourth = rank[3]
    num1 = rank.count(second)
    num2 = rank.count(fourth)
    return [second, fourth, next((c for c in rank if rank.count(c) == 1), rank[0])], num1 == 2 and num2 == 2

def is_one_pair(hand):
    rank = get_rank(hand)
    pairs = [c for c in set(rank) if rank.count(c) == 2]
    result = []
    if pairs:
        result.append(pairs[0])
    result.extend(c for c in rank if rank.count(c) == 1)
    return result, len(pairs) == 1

def highest(hands, ranks):
    result = []
    high = ranks[0]
    for rank in ranks:
        if larger(rank, high):
            high = rank

    for i in range(len(ranks)):
        if high == ranks[i]:
            result.append(hands[i])

    return result

def check_ace(rank):
    if all(rank[n] + 1 == rank[n - 1] for n in range(2, 5)) and rank[1] == 5 and rank[0] == 14:
        rank[0] = 1
        rank.sort(reverse=True)

def larger(lst1, lst2):
    for i in range(len(lst1)):
        if lst1[i] > lst2[i]:
            return True
        if lst1[i] < lst2[i]:
            return False

    return False

def get_rank(hand):
    return sorted([rank_values[c[0:-1]] for c in hand.split(" ")], reverse=True)

def get_suit(hand):
    return [c[-1] for c in hand.split(" ")]