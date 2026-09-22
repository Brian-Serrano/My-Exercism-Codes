def is_armstrong_number(number):
    return number == sum(int(x) ** len(str(number)) for x in str(number))
