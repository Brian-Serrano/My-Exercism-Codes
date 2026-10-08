def find_fewest_coins(coins, target):
    if target == 0:
        return []
    if target < 0:
        raise ValueError("target can't be negative")

    possible_coins = [[c] for c in coins]
    while not any(sum(c) == target for c in possible_coins):
        possible_coins = [list(x) for x in list(set([tuple(x) for c in possible_coins for x in compute(c, coins)]))]

        if all(sum(c) > target for c in possible_coins):
            raise ValueError("can't make target with given coins")

    return [c for c in possible_coins if sum(c) == target][0]

def compute(coin, coins):
    result = []
    for c in coins:
        res = coin.copy()
        res.append(c)
        res.sort()
        result.append(res)
    return result