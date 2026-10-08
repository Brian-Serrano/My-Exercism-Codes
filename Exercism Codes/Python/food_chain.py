phrases = [
    ["I know an old lady who swallowed a fly.", "I don't know why she swallowed the fly. Perhaps she'll die."],
    ["I know an old lady who swallowed a spider.", "It wriggled and jiggled and tickled inside her."],
    ["I know an old lady who swallowed a bird.", "How absurd to swallow a bird!"],
    ["I know an old lady who swallowed a cat.", "Imagine that, to swallow a cat!"],
    ["I know an old lady who swallowed a dog.", "What a hog, to swallow a dog!"],
    ["I know an old lady who swallowed a goat.", "Just opened her throat and swallowed a goat!"],
    ["I know an old lady who swallowed a cow.", "I don't know how she swallowed a cow!"],
    ["I know an old lady who swallowed a horse.", "She's dead, of course!"]
]

other_phrases = [
    ["She swallowed the spider to catch the fly.", "I don't know why she swallowed the fly. Perhaps she'll die."],
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    "She swallowed the cat to catch the bird.",
    "She swallowed the dog to catch the cat.",
    "She swallowed the goat to catch the dog.",
    "She swallowed the cow to catch the goat."
]


def recite(start_verse, end_verse):
    phrase_groups = []
    for i in range(start_verse, end_verse + 1):
        phrase_groups.extend(recite_verse(i))
        if i != end_verse:
            phrase_groups.append("")

    return phrase_groups


def recite_verse(verse):
    phrase_group = []

    if verse > 7:
        return phrases[7]

    for i in range(verse - 1, -1, -1):
        if i == verse - 1:
            phrase_group.extend(phrases[i])
        else:
            if i == 0:
                phrase_group.extend(other_phrases[i])
            else:
                phrase_group.append(other_phrases[i])

    return phrase_group