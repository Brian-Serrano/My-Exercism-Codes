phr = [" the house that Jack built.",
       " the malt ",
       " the rat ",
       " the cat ",
       " the dog ",
       " the cow with the crumpled horn ",
       " the maiden all forlorn ",
       " the man all tattered and torn ",
       " the priest all shaven and shorn ",
       " the rooster that crowed in the morn ",
       " the farmer sowing his corn ",
       " the horse and the hound and the horn "]

wrd = ["lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"]


def recite(start_verse, end_verse):
    return [rec(i) for i in range(start_verse, end_verse + 1)]


def rec(verse):
    return ''.join([("This is" if i == verse - 1 else "that " + wrd[i]) + phr[i] for i in range(verse - 1, 0 - 1, -1)])
