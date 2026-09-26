def sum_of_multiples(limit, multiples):
    return sum([x for x in range(limit) if any(x % m == 0 if m > 0 else False for m in multiples)])
