import java.util.ArrayList;
import java.util.List;

public class Dominoes {
    public List<Domino> formChain(List<Domino> dominoes) throws ChainNotFoundException {
        List<Domino> input = new ArrayList<>(dominoes), output = new ArrayList<>();
        int counter = 0, anotherCounter = 0;
        if(input.size() > 0) output.add(input.remove(0));
        while(true) {
            if(input.isEmpty()) {
                if(output.isEmpty()) break;
                if(output.get(0).getLeft() != output.get(output.size() - 1).getRight())
                    throw new ChainNotFoundException("No domino chain found.");
                break;
            }
            if(output.get(0).getLeft() == input.get(0).getLeft()) {
                output.add(0, new Domino(input.get(0).getRight(), input.get(0).getLeft()));
                input.remove(0);
                counter = 0;
                anotherCounter = 0;
                continue;
            }
            if(output.get(0).getLeft() == input.get(0).getRight()) {
                output.add(0, input.get(0));
                input.remove(0);
                counter = 0;
                anotherCounter = 0;
                continue;
            }
            if(output.get(output.size() - 1).getRight() == input.get(0).getLeft()) {
                output.add(input.get(0));
                input.remove(0);
                counter = 0;
                anotherCounter = 0;
                continue;
            }
            if(output.get(output.size() - 1).getRight() == input.get(0).getRight()) {
                output.add(new Domino(input.get(0).getRight(), input.get(0).getLeft()));
                input.remove(0);
                counter = 0;
                anotherCounter = 0;
                continue;
            }
            input.add(input.remove(0));
            if(++counter >= input.size()) {
                if(output.get(0).getLeft() != output.get(output.size() - 1).getRight())
                    throw new ChainNotFoundException("No domino chain found.");
                else {
                    output.add(output.remove(0));
                    counter = 0;
                    if(++anotherCounter >= output.size()) throw new ChainNotFoundException("No domino chain found.");
                }

            }
        }
        return output;
    }
}
