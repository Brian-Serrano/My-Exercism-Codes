def answer(question):
    question = [x for x in question[:-1].split()[2:] if x != "by"]
    if any(not(is_number(i) or i in ["plus", "minus", "multiplied", "divided"]) for i in question):
        raise ValueError("unknown operation")
    try:
        if len(question) % 2 == 0:
            raise ValueError("syntax error")
        count = int(question[0])
        for x in range(2, len(question), 2):
            if question[x - 1] == "plus":
                count += int(question[x])
            elif question[x - 1] == "minus":
                count -= int(question[x])
            elif question[x - 1] == "multiplied":
                count *= int(question[x])
            elif question[x - 1] == "divided":
                count /= int(question[x])
            else:
                raise ValueError("syntax error")
    except:
        raise ValueError("syntax error")

    return count


def is_number(string):
    return string.isdigit() if string[0] != '-' else string[1:].isdigit()
