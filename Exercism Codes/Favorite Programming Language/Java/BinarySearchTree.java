import java.util.*;

class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    void insert(T value) {
        if(root != null) insert(value, root);
        else {
            root = new Node<>();
            root.data = value;
        }
    }

    void insert(T value, Node<T> node) {
        if(node.data.compareTo(value) >= 0) {
            if(node.left != null) {
                insert(value, node.left);
            }
            else {
                node.left = new Node<>();
                node.left.data = value;
            }
        }
        else {
            if(node.right != null) {
                insert(value, node.right);
            }
            else {
                node.right = new Node<>();
                node.right.data = value;
            }
        }
    }

    List<T> getAsSortedList() {
        return inOrderTraversal(root, new ArrayList<>());
    }

    List<T> inOrderTraversal(Node<T> node, List<T> nodeData) {
        if(node != null) {
            inOrderTraversal(node.left, nodeData);
            nodeData.add(node.data);
            inOrderTraversal(node.right, nodeData);
        }
        return nodeData;
    }

    List<T> getAsLevelOrderList() {
        return breadthFirstTraversal(root, new ArrayList<>());
    }

    List<T> breadthFirstTraversal(Node<T> node, List<T> nodeData) {
        if(node != null) {
            List<Node<T>> queue = new ArrayList<>();
            queue.add(node);
            while(!queue.isEmpty()) {
                Node<T> n = queue.remove(0);
                nodeData.add(n.data);
                if(n.left != null) queue.add(n.left);
                if(n.right != null) queue.add(n.right);
            }
        }
        return nodeData;
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {

        Node<T> left, right;
        T data;

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

    }
}
