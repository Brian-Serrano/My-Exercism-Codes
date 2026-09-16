from collections import Counter
from itertools import combinations


def total(basket: list):
    cnt = Counter(basket)
    freq = max(cnt.values(), default=0)
    combination = find_combinations(dict(cnt), freq, list(cnt.keys()), 0, [[0] * freq])
    discounts = {1: 0.0, 2: 0.4, 3: 0.8, 4: 1.6, 5: 2.0}
    return min(map_combinations(lst, discounts) for lst in combination)

def map_combinations(lst, discounts):
    return sum(100 * b * (8.0 - discounts[b]) for b in lst)


def find_combinations(cnt, freq, nums, num, arr):
    if num == len(nums):
        return arr
    else:
        arr_2 = arr.copy()
        arr = []
        for x in arr_2:
            for positions in combinations(range(freq), cnt[nums[num]]):
                new_arr = x.copy()
                for j in positions:
                    new_arr[j] += 1
                arr.append(new_arr)
        return find_combinations(cnt, freq, nums, num + 1, arr)