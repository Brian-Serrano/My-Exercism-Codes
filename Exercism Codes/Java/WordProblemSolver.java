import java.util.Arrays;
import java.util.List;

class WordProblemSolver {
    int solve(final String wordProblem) {
        List<String> op = Arrays.asList("plus", "minus", "multiplied", "divided");
        String[] newQuestion = Arrays.stream(wordProblem.substring(0, wordProblem.length() - 1).split(" ")).skip(2).filter(x -> !x.equals("by")).toArray(String[]::new);
        if(newQuestion.length == 0 || Arrays.stream(newQuestion).anyMatch(x -> !(isNumber(x) || op.contains(x)))) throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        int count;
        try {
            if(newQuestion.length % 2 == 0) throw new IllegalArgumentException();
            count = Integer.parseInt(newQuestion[0]);
            for(int i = 2; i < newQuestion.length; i += 2) {
                if(newQuestion[i - 1].equals(op.get(0))) count += Integer.parseInt(newQuestion[i]);
                else if(newQuestion[i - 1].equals(op.get(1))) count -= Integer.parseInt(newQuestion[i]);
                else if(newQuestion[i - 1].equals(op.get(2))) count *= Integer.parseInt(newQuestion[i]);
                else if(newQuestion[i - 1].equals(op.get(3))) count /= Integer.parseInt(newQuestion[i]);
                else throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }
        }
        catch(Exception e) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }
        return count;
    }

    boolean isNumber(String question) {
        try {
            int n = Integer.parseInt(question);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
