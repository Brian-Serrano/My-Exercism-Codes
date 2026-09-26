def maximum_value(maximum_weight, items):
    dp = [[0] * (maximum_weight + 1) for _ in range(len(items) + 1)]

    for i in range(1, len(items) + 1):
        item = items[i - 1]
        for j in range(1, maximum_weight + 1):
            if item["weight"] <= j:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - item["weight"]] + item["value"])
            else:
                dp[i][j] = dp[i - 1][j]

    return dp[len(items)][maximum_weight]