def response(hey_bob):
    hey_bob = hey_bob.strip()
    if len(hey_bob) == 0:
        return "Fine. Be that way!"
    if hey_bob[-1] == '?' and hey_bob == hey_bob.upper() and any(x.isalpha() for x in hey_bob):
        return "Calm down, I know what I'm doing!"
    if hey_bob[-1] == '?':
        return "Sure."
    if hey_bob == hey_bob.upper() and any(x.isalpha() for x in hey_bob):
        return "Whoa, chill out!"

    return "Whatever."
