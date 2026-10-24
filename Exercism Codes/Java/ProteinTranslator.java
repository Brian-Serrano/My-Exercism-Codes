import java.util.List;
import java.util.ArrayList;

class ProteinTranslator {

    List<String> translate(String rnaSequence) {
        List<String> splittedRna = split(rnaSequence);
        List<String> proteins = new ArrayList<>();
        for (int i = 0; i < splittedRna.size(); i++) {
            String s = splittedRna.get(i);
            if (s.equals("AUG")) {
                proteins.add("Methionine");
                continue;
            }
            if (s.equals("UUU") || s.equals("UUC")) {
                proteins.add("Phenylalanine");
                continue;
            }
            if (s.equals("UUA") || s.equals("UUG")) {
                proteins.add("Leucine");
                continue;
            }
            if (s.equals("UCU") || s.equals("UCC") || s.equals("UCA") || s.equals("UCG")) {
                proteins.add("Serine");
                continue;
            }
            if (s.equals("UAU") || s.equals("UAC")) {
                proteins.add("Tyrosine");
                continue;
            }
            if (s.equals("UGU") || s.equals("UGC")) {
                proteins.add("Cysteine");
                continue;
            }
            if (s.equals("UGG")) {
                proteins.add("Tryptophan");
                continue;
            }
            if (s.equals("UAA") || s.equals("UAG") || s.equals("UGA")) {
                break;
            }
            throw new IllegalArgumentException("Invalid codon");
        }
        return proteins;
    }
    List<String> split(String rnaSequence) {
        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < rnaSequence.length(); i += 3) {
            int endIndex = Math.min(i + 3, rnaSequence.length());
            String substring = rnaSequence.substring(i, endIndex);
            substrings.add(substring);
        }

        return substrings;
    }
}
