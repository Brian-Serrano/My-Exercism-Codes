import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class ForthEvaluator {

    List<Integer> evaluateProgram(List<String> instructions) {
        List<String> tokens = new ArrayList<>();
        List<List<String>> func = new ArrayList<>();
        List<String> funcName = new ArrayList<>();

        for (String instruction : instructions) {
            List<String> f = Pattern.compile("[^ :;]+").matcher(instruction.toLowerCase())
                    .results().map(MatchResult::group).toList();
            if (instruction.matches("(^:.*;$)")) {
                if (isNumber(f.get(0))) throw new IllegalArgumentException("Cannot redefine numbers");
                funcName.add(0, f.get(0));
                func.add(0, f.subList(1, f.size()));
            } else {
                tokens.addAll(f);
            }
        }
        return call(new Stack<>(), tokens, func, funcName, 0).stream().toList();
    }

    Stack<Integer> call(Stack<Integer> stack, List<String> tokens, List<List<String>> func, List<String> funcName, int where) {
        for (String token : tokens) {
            int index = funcName.indexOf(token);
            if (index != -1) {
                call(stack, func.subList(where, func.size()).get(index), func, funcName, index + 1);
                continue;
            }
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }
            if (token.equals("+")) {
                if(stack.size() < 2) throw new IllegalArgumentException("Addition requires that the stack contain at least 2 values");
                stack.push(stack.pop() + stack.pop());
                continue;
            }
            if (token.equals("-")) {
                if(stack.size() < 2) throw new IllegalArgumentException("Subtraction requires that the stack contain at least 2 values");
                int first = stack.pop();
                int second = stack.pop();
                stack.push(second - first);
                continue;
            }
            if (token.equals("*")) {
                if(stack.size() < 2) throw new IllegalArgumentException("Multiplication requires that the stack contain at least 2 values");
                stack.push(stack.pop() * stack.pop());
                continue;
            }
            if (token.equals("/")) {
                if(stack.size() < 2) throw new IllegalArgumentException("Division requires that the stack contain at least 2 values");
                int first = stack.pop();
                if(first == 0) throw new IllegalArgumentException("Division by 0 is not allowed");
                int second = stack.pop();
                stack.push(second / first);
                continue;
            }
            if (token.equals("dup")) {
                if(stack.size() < 1) throw new IllegalArgumentException("Duplicating requires that the stack contain at least 1 value");
                stack.push(stack.peek());
                continue;
            }
            if (token.equals("drop")) {
                if(stack.size() < 1) throw new IllegalArgumentException("Dropping requires that the stack contain at least 1 value");
                stack.pop();
                continue;
            }
            if (token.equals("swap")) {
                if(stack.size() < 2) throw new IllegalArgumentException("Swapping requires that the stack contain at least 2 values");
                int first = stack.pop();
                int second = stack.pop();
                stack.push(first);
                stack.push(second);
                continue;
            }
            if (token.equals("over")) {
                if(stack.size() < 2) throw new IllegalArgumentException("Overing requires that the stack contain at least 2 values");
                stack.push(stack.elementAt(stack.size() - 2));
                continue;
            }
            throw new IllegalArgumentException("No definition available for operator \"" + token + "\"");
        }
        return stack;
    }

    boolean isNumber(String token) {
        try {
            int n = Integer.parseInt(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
