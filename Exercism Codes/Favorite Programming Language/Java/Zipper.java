import java.util.Objects;

class Zipper {

    Zipper left, right, up;
    int value;

    Zipper(int val) {
        value = val;
    }

    BinaryTree toTree() {
        return up != null ? up.toTree() : new BinaryTree(this);
    }

    int getValue() {
        return value;
    }

    void setLeft(Zipper leftChild) {
        if((left = leftChild) != null) left.up = this;
    }

    void setRight(Zipper rightChild) {
        if((right = rightChild) != null) right.up = this;
    }

    void setValue(int val) {
        value = val;
    }

    @Override
    public String toString() {
        return "value: " + value + ", left: " +
                (left == null ? null : "{ " + left + " }") + ", right: " +
                (right == null ? null : "{ " + right + " }");
    }
}

class BinaryTree {

    Zipper root;

    BinaryTree(int value) {
        root = new Zipper(value);
    }

    BinaryTree(Zipper root) {
        this.root = root;
    }

    Zipper getRoot() {
        return root;
    }

    String printTree() {
        return root.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
