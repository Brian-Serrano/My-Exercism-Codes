//
// This is only a SKELETON file for the 'Nucleotide Count' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export function countNucleotides(strand) {
  if (!/^[ACGT]*$/.test(strand))
    throw new Error("Invalid nucleotide in strand");
  
  const count = { "A": 0, "C": 0, "G": 0, "T": 0 };
  for (const s of strand.split("")) {
    count[s]++;
  }
  return Object.values(count).join(" ");
}
