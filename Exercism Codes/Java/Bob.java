import java.util.regex.Pattern;

public class Bob {

    public String hey(String phrase) {
        phrase = phrase.trim();

        if(phrase.endsWith("?") &&
                phrase.toUpperCase().equals(phrase) &&
                Pattern.compile(".*[a-zA-Z].*").matcher(phrase).matches()
        ) {
            return "Calm down, I know what I'm doing!";
        }
        else if(phrase.endsWith("?")) {
            return "Sure.";
        }
        else if(phrase.equals("")) {
            return "Fine. Be that way!";
        }
        else if(phrase.toUpperCase().equals(phrase) &&
                Pattern.compile(".*[a-zA-Z].*").matcher(phrase).matches()
        ) {
            return "Whoa, chill out!";
        }
        else {
            return "Whatever.";
        }
    }
}
