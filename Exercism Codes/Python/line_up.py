def line_up(name, number):
    ending = "th"
    n = str(number)
    if n[-1] == "1" and not (len(n) > 1 and n[-2:] == "11"):
        ending = "st"
    if n[-1] == "2" and not (len(n) > 1 and n[-2:] == "12"):
        ending = "nd"
    if n[-1] == "3" and not (len(n) > 1 and n[-2:] == "13"):
        ending = "rd"

    return f"{name}, you are the {number}{ending} customer we serve today. Thank you!"
