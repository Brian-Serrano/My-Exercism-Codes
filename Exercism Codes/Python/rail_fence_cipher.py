def encode(message, rails):
    return ''.join(message[x] for x in create_fence(len(message), rails))


def decode(encoded_message, rails):
    fence = create_fence(len(encoded_message), rails)
    return ''.join(encoded_message[fence.index(x)] for x in range(len(encoded_message)))


def create_fence(msg_len, rails):
    cycle = 2 * rails - 2
    return flatten([[y for y in range(msg_len) if y % cycle == x or y % cycle == cycle - x] for x in range(rails)])


def flatten(lst):
    return [item for sublist in lst for item in sublist]