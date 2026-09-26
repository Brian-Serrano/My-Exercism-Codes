def primes(limit):
    prime = [True] * (limit + 1)
    p = 2
    result = []
    while p * p <= limit:
        if prime[p]:
            for i in range(p * p, limit + 1, p):
                prime[i] = False
        p += 1
    for i in range(2, limit + 1):
        if prime[i]:
            result.append(i)
    return result