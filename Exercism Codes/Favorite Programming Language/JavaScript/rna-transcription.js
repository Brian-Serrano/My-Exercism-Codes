const dict = { "G": "C", "C": "G", "T": "A", "A": "U" };
export const toRna = (rna) => {
  return rna.split("").map(r => dict[r]).join("");
};
