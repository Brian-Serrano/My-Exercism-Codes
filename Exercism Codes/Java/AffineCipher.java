import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AffineCipher {

    // A should not be divisible 13 or 2 Alphabet size has factors 1 2 13 26
    public String encode(String text, int coefficient1, int coefficient2){
        if(coefficient1 % 2 == 0 || coefficient1 % 13 == 0)
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        String enc = text.toLowerCase()
                .replaceAll("\\W", "").chars()
                .map(x -> x >= 48 && x <= 57 ? x - 'a' : ((coefficient1 * (x - 'a')) + coefficient2) % 26)
                .mapToObj(x -> String.valueOf((char)(x + 'a')))
                .collect(Collectors.joining());
        return Pattern.compile("(.{1,5})").matcher(enc)
                .results().map(MatchResult::group)
                .collect(Collectors.joining(" "));
    }

    public String decode(String text, int coefficient1, int coefficient2){
        if(coefficient1 % 2 == 0 || coefficient1 % 13 == 0)
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        return text.toLowerCase().replaceAll("\\W", "").chars()
                .map(x -> x >= 48 && x <= 57 ? x : (mmi(coefficient1) * ((x - 'a') - coefficient2)) % 26)
                .mapToObj(x -> x >= 48 && x <= 57 ? String.valueOf((char)x) :
                        String.valueOf((char)(((x + 26) % 26) + 'a')))
                .collect(Collectors.joining());
    }

    private int mmi(int a) {
        for(int i = 1; i < 26; i++) {
            if((a * i) % 26 == 1) return i;
        }
        return -1;
    }
}