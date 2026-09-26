import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PigLatinTranslator {
	private String vowels = "aeiou";
	
	public String translate(String phrase) {
		List<String> words = Arrays.stream(phrase.split(" ")).map(w -> {
			StringBuilder sb = new StringBuilder(w);
			while(true) {
				String ss = sb.substring(0, 2);
				if(ss.equals("qu")) {
					sb.delete(0, 2).append("qu");
				}
				else if(ss.equals("yt") || ss.equals("xr")) {
					break;
				}
				else if(sb.substring(0, 1).equals("y") && 
						((!vowels.contains(sb.subSequence(1, 2)) && 
						!vowels.contains(sb.subSequence(sb.length() - 1, sb.length())) || 
						sb.length() == 2)
						)) {
					break;
				}
				else if(!vowels.contains(sb.subSequence(0, 1))) {
					char charToReplace = sb.charAt(0);
					sb.deleteCharAt(0).append(charToReplace);
				}
				else {
					break;
				}
			}
			return sb.append("ay").toString();
		}).collect(Collectors.toList());
		
		return String.join(" ", words);
	}
}