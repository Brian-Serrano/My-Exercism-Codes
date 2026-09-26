import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Satellite {

    private int preorderIndex = 0;
    public Tree treeFromTraversals(List<Character> preorderInput, List<Character> inorderInput) {
        Set<Character> inOrder = new HashSet<>(inorderInput);
        Set<Character> preOrder = new HashSet<>(preorderInput);
        if(inorderInput.size() != inOrder.size() || preorderInput.size() != preOrder.size())
            throw new IllegalArgumentException("traversals must contain unique items");
        if(preorderInput.size() != inorderInput.size())
            throw new IllegalArgumentException("traversals must have the same length");
        if(!inOrder.equals(preOrder))
            throw new IllegalArgumentException("traversals must have the same elements");
        return new Tree(buildTree(inorderInput, preorderInput, 0, inorderInput.size() - 1));
    }

    Node buildTree(List<Character> inorderInput, List<Character> preorderInput, int start, int end) {
        if (start > end) return null;
        Node n = new Node(preorderInput.get(preorderIndex++));
        if (start == end) return n;
        int inorderIndex = search(inorderInput, start, end, n.value);
        n.left = buildTree(inorderInput, preorderInput, start, inorderIndex - 1);
        n.right = buildTree(inorderInput, preorderInput, inorderIndex + 1, end);
        return n;
    }

    int search(List<Character> inorderInput, int start, int end, char value) {
        int i;
        for (i = start; i <= end; i++) if (inorderInput.get(i) == value) return i;
        return i;
    }
}
