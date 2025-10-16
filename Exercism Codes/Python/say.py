ones = ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
tens = ["", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
teens = ["", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]

def say(number):
    if number == 0:
        return "zero"
    if number < 0 or number > 999999999999:
        raise ValueError("input out of range")

    result = []
    stringed = str(number)

    for x in range(len(stringed) - 1, -1, -1):
        idx = len(stringed) - x - 1
        if idx / 3 == 1:
            result.insert(0, "thousand")
        if idx / 3 == 2:
            if int(stringed[x + 1: x + 4]) == 0:
                result.pop(0)
            result.insert(0, "million")
        if idx / 3 == 3:
            if int(stringed[x + 1: x + 4]) == 0:
                result.pop(0)
            result.insert(0, "billion")

        if int(stringed[x]) != 0:
            if idx % 3 == 0:
                result.insert(0, ones[int(stringed[x])])
            if idx % 3 == 1:
                if int(stringed[x]) == 1 and int(stringed[x + 1]) != 0:
                    result.pop(0)
                    result.insert(0, teens[int(stringed[x + 1])])
                else:
                    result.insert(0, tens[int(stringed[x])])
            if idx % 3 == 2:
                result.insert(0, "hundred")
                result.insert(0, ones[int(stringed[x])])

    return join(result)

def join(lst):
    result = lst[0]

    for x in range(1, len(lst)):
        if lst[x - 1] in tens and lst[x] in ones:
            result += "-" + lst[x]
        else:
            result += " " + lst[x]

    return result