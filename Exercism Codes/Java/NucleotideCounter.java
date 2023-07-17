import java.util.HashMap;
import java.util.Map;

public class NucleotideCounter {
    private String nucleotide;

    public NucleotideCounter(String nucleotide) {
        if(!nucleotide.matches("^[ACGT]*$"))
            throw new IllegalArgumentException();
        else
            this.nucleotide = nucleotide;
    }

    public Map nucleotideCounts() {
        Map<Character, Integer> nucleotideCount = new HashMap<>();
        nucleotideCount.put('A', 0);
        nucleotideCount.put('C', 0);
        nucleotideCount.put('G', 0);
        nucleotideCount.put('T', 0);
        for(int i = 0; i < nucleotide.length(); i++) {
            switch(nucleotide.charAt(i)) {
                case 'A':
                    nucleotideCount.put('A', nucleotideCount.get('A') + 1);
                    break;
                case 'C':
                    nucleotideCount.put('C', nucleotideCount.get('C') + 1);
                    break;
                case 'G':
                    nucleotideCount.put('G', nucleotideCount.get('G') + 1);
                    break;
                case 'T':
                    nucleotideCount.put('T', nucleotideCount.get('T') + 1);
                    break;
            }
        }
        return nucleotideCount;
    }
}