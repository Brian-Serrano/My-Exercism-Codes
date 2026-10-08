def encode(numbers):
    result = []
    for i in range(len(numbers)):
        if numbers[i] == 0:
            result.append(0)
            continue

        res = []
        while numbers[i] > 0:
            res.append(numbers[i] & 0x7F)
            numbers[i] >>= 7
        for j in range(1, len(res)):
            res[j] |= 0x80
        res.reverse()
        result.extend(res)

    return result


def decode(bytes_):
    if bytes_[-1] & 0x80 != 0:
        raise ValueError("incomplete sequence")

    result = []
    c = 0
    result.append(0)
    for i in range(len(bytes_)):
        result[c] = (result[c] << 7) | (bytes_[i] & 0x7F)
        if (bytes_[i] & 0x80) == 0 and len(bytes_) - 1 != i:
            result.append(0)
            c += 1
    return result