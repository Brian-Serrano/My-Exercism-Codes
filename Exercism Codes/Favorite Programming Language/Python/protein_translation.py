def proteins(strand):
    lst = []
    for x in range(0, len(strand), 3):
        s = strand[x:x + 3]
        if s in ["AUG"]:
            lst.append("Methionine")
        elif s in ["UUU", "UUC"]:
            lst.append("Phenylalanine")
        elif s in ["UUA", "UUG"]:
            lst.append("Leucine")
        elif s in ["UCU", "UCC", "UCA", "UCG"]:
            lst.append("Serine")
        elif s in ["UAU", "UAC"]:
            lst.append("Tyrosine")
        elif s in ["UGU", "UGC"]:
            lst.append("Cysteine")
        elif s in ["UGG"]:
            lst.append("Tryptophan")
        elif s in ["UAA", "UAG", "UGA"]:
            break

    return lst
