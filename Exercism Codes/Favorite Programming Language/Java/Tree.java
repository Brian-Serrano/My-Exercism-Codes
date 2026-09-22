import java.util.*;

class Tree {
    public final String label;
    public final List<Tree> children;

    public Tree(String label) {
        this(label, new ArrayList<>());
    }

    public Tree(String label, List<Tree> children) {
        this.label = label;
        this.children = children;
    }

    public static Tree of(String label) {
        return new Tree(label);
    }

    public static Tree of(String label, List<Tree> children) {
        return new Tree(label, children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return label.equals(tree.label)
                && children.size() == tree.children.size()
                && children.containsAll(tree.children)
                && tree.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label +
                ", " + children +
                "}";
    }

    public Tree fromPov(String fromNode) {
        Map<String, Neighbor> treeData = getNeighbors(this, new HashMap<>(), null);
        if(!treeData.containsKey(fromNode)) throw new UnsupportedOperationException("Tree could not be reoriented");
        return constructTreeFromNode(treeData, fromNode, null);
    }

    public List<String> pathTo(String fromNode, String toNode) {
        try {
            Map<String, Neighbor> treeData = getNeighbors(fromPov(fromNode), new HashMap<>(), null);
            if(!treeData.containsKey(toNode)) throw new UnsupportedOperationException("No path found");
            return findPath(new ArrayList<>(Arrays.asList(fromNode)), treeData, fromNode, toNode, new HashSet<>());
        }
        catch(UnsupportedOperationException e) {
            throw new UnsupportedOperationException("No path found");
        }
    }

    private Map<String, Neighbor> getNeighbors(Tree tree, Map<String, Neighbor> trees, Tree parent) {
        Neighbor neighbors = new Neighbor();
        if(parent != null) neighbors.parent = parent.label;
        if(tree.children.size() != 0) {
            for(Tree child : tree.children) {
                neighbors.children.add(child.label);
                getNeighbors(child, trees, tree);
            }
        }
        trees.put(tree.label, neighbors);
        return trees;
    }

    private Tree constructTreeFromNode(Map<String, Neighbor> treeData, String nodeLabel, String parent) {
        List<Tree> child = new ArrayList<>();
        Neighbor neighbor = treeData.get(nodeLabel);
        List<String> neighborList = new ArrayList<>();
        if(neighbor.parent != null) neighborList.add(neighbor.parent);
        neighborList.addAll(neighbor.children);
        for(int i = 0; i < neighborList.size(); i++) {
            String node = neighborList.get(i);
            if(!Objects.equals(node, parent)) {
                child.add(constructTreeFromNode(treeData, node, nodeLabel));
            }
        }
        return new Tree(nodeLabel, child);
    }

    public static List<String> findPath(List<String> path, Map<String, Neighbor> treeData, String nodeLabel, String toLabel, Set<String> doNotGoThisPathAnymore) {
        if(Objects.equals(nodeLabel, toLabel)) return path;
        Neighbor neighbor = treeData.get(nodeLabel);
        if(neighbor.children.size() == 0 || doNotGoThisPathAnymore.containsAll(neighbor.children)) {
            path.remove(nodeLabel);
            doNotGoThisPathAnymore.add(nodeLabel);
            return findPath(path, treeData, neighbor.parent, toLabel, doNotGoThisPathAnymore);
        }
        else {
            for(int i = 0; i < neighbor.children.size(); i++) {
                String node = neighbor.children.get(i);
                if(!doNotGoThisPathAnymore.contains(node)) {
                    path.add(node);
                    return findPath(path, treeData, node, toLabel, doNotGoThisPathAnymore);
                }
            }
        }
        return path;
    }
}

class Neighbor {
    public String parent;
    public List<String> children;

    public Neighbor() {
        children = new ArrayList<>();
    }
}
