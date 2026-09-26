def can_chain(dominoes):
    inp = dominoes.copy()
    output = []
    x, y = 0, 0

    if len(inp) > 0:
        output.append(inp.pop(0))

    while True:
        if len(inp) == 0:
            if len(output) == 0:
                break
            if output[0][0] != output[-1][1]:
                return None
            break
        if output[0][0] == inp[0][0]:
            output.insert(0, (inp[0][1], inp[0][0]))
            inp.pop(0)
            x = 0
            y = 0
            continue
        if output[0][0] == inp[0][1]:
            output.insert(0, inp[0])
            inp.pop(0)
            x = 0
            y = 0
            continue
        if output[-1][1] == inp[0][0]:
            output.append(inp[0])
            inp.pop(0)
            x = 0
            y = 0
            continue
        if output[-1][1] == inp[0][1]:
            output.append((inp[0][1], inp[0][0]))
            inp.pop(0)
            x = 0
            y = 0
            continue
        inp.append(inp.pop(0))
        x += 1
        if x >= len(inp):
            if output[0][0] != output[-1][1]:
                return None
            else:
                output.append(output.pop(0))
                x = 0
                y += 1
                if y >= len(output):
                    return None
    return output