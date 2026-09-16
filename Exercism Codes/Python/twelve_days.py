phrases = [
    "a Partridge in a Pear Tree",
    "two Turtle Doves",
    "three French Hens",
    "four Calling Birds",
    "five Gold Rings",
    "six Geese-a-Laying",
    "seven Swans-a-Swimming",
    "eight Maids-a-Milking",
    "nine Ladies Dancing",
    "ten Lords-a-Leaping",
    "eleven Pipers Piping",
    "twelve Drummers Drumming"
]

number = [
    "first", "second", "third",
    "fourth", "fifth", "sixth",
    "seventh", "eighth", "ninth",
    "tenth", "eleventh", "twelfth"
]

def recite(start_verse, end_verse):
    verse = []
    for i in range(start_verse, end_verse + 1):
        verse.append(generate_verse(i))
    return verse

def generate_verse(verse_number):
    verse = "On the " + number[verse_number - 1] + " day of Christmas my true love gave to me: "
    for i in range(verse_number - 1, -1, -1):
        seperator = "" if i == 0 else ", "
        if i == 0 and verse_number > 1:
            verse += "and "
        verse += phrases[i] + seperator
    verse += "."
    return verse