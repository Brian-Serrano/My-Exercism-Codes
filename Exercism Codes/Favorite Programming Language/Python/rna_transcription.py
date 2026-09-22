def to_rna(dna_strand):
    lst = {"C": "G", "G": "C", "T": "A", "A": "U"}
    return ''.join(lst[x] for x in dna_strand)