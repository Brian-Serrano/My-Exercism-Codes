def commands(binary_str):
    binary_str = binary_str[::-1]
    lst = ["wink", "double blink", "close your eyes", "jump"]
    res = [lst[x] for x in range(4) if binary_str[x] == '1']
    if binary_str[-1] == '1':
        res.reverse()
    return res

