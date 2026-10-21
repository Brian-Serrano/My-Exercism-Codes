numbers = [
    ["One", "no"],
    ["Two", "one"],
    ["Three", "two"],
    ["Four", "three"],
    ["Five", "four"],
    ["Six", "five"],
    ["Seven", "six"],
    ["Eight", "seven"],
    ["Nine", "eight"],
    ["Ten", "nine"],
]

def recite(start, take=1):
    result = []
    for i in range(start, start - take, -1):
        n = numbers[i - 1][0]
        bottle1 = "bottle" if n == "One" else "bottles"
        result.append(f"{n} green {bottle1} hanging on the wall,")
        result.append(f"{n} green {bottle1} hanging on the wall,")

        result.append("And if one green bottle should accidentally fall,")

        s = numbers[i - 1][1]
        bottle2 = "bottle" if s == "one" else "bottles"
        result.append(f"There'll be {s} green {bottle2} hanging on the wall.")

        if i != start - take + 1:
            result.append("")

    return result

