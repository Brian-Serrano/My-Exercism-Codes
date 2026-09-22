import java.util.List;
import java.util.ArrayList;

class ProteinTranslator {

    List<String> translate(String rnaSequence) {
        List<String> splittedRna = split(rnaSequence);
        List<String> proteins = new ArrayList<>();
        for(int i=0; i<splittedRna.size(); i++){
        switch (splittedRna.get(i)){
            case "AUG":
                proteins.add("Methionine");
                break;
            case "UUU":
                proteins.add("Phenylalanine");
                break;
            case "UUC":
                proteins.add("Phenylalanine");
                break;
            case "UUA":
                proteins.add("Leucine");
                break;
            case "UUG":
                proteins.add("Leucine");
                break;
            case "UCU":
                proteins.add("Serine");
                break;
            case "UCC":
                proteins.add("Serine");
                break;
            case "UCA":
                proteins.add("Serine");
                break;
            case "UCG":
                proteins.add("Serine");
                break;
            case "UAU":
                proteins.add("Tyrosine");
                break;
            case "UAC":
                proteins.add("Tyrosine");
                break;
            case "UGU":
                proteins.add("Cysteine");
                break;
            case "UGC":
                proteins.add("Cysteine");
                break;
            case "UGG":
                proteins.add("Tryptophan");
                break;
            }
            if(splittedRna.get(i).equals("UAA") || splittedRna.get(i).equals("UAG") || splittedRna.get(i).equals("UGA")){
                break;
            }
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