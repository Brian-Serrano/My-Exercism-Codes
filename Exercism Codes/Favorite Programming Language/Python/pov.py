from json import dumps


class Tree:
    def __init__(self, label, children=None):
        self.label = label
        self.children = children if children is not None else []

    def __dict__(self):
        return {self.label: [c.__dict__() for c in sorted(self.children)]}

    def __str__(self, indent=None):
        return dumps(self.__dict__(), indent=indent)

    def __lt__(self, other):
        return self.label < other.label

    def __eq__(self, other):
        return self.__dict__() == other.__dict__()

    def from_pov(self, from_node):
        tree_data = self.get_neighbors(self, {}, None)
        if not from_node in tree_data:
            raise ValueError("Tree could not be reoriented")
        return self.construct_tree_from_node(tree_data, from_node, None)

    def path_to(self, from_node, to_node):
        tree_data = self.get_neighbors(self.from_pov(from_node), {}, None)
        if not all(node in tree_data for node in (from_node, to_node)):
            raise ValueError("No path found")
        return self.find_path([from_node], tree_data, from_node, to_node, set())

    def get_neighbors(self, tree, trees, parent):
        neighbors = Neighbor()
        if parent:
            neighbors.parent = parent.label
        if len(tree.children) != 0:
            for child in tree.children:
                neighbors.children.append(child.label)
                self.get_neighbors(child, trees, tree)
        trees[tree.label] = neighbors
        return trees

    def construct_tree_from_node(self, tree_data, node_label, parent_label):
        children = []
        neighbor = tree_data[node_label]
        neighbor_list = []
        if neighbor.parent:
            neighbor_list.append(neighbor.parent)
        neighbor_list.extend(neighbor.children)
        for i in range(len(neighbor_list)):
            if neighbor_list[i] != parent_label:
                children.append(self.construct_tree_from_node(tree_data, neighbor_list[i], node_label))
        return Tree(node_label, children)

    def find_path(self, path, tree_data, node_label, to_label, visited):
        if node_label == to_label:
            return path
        neighbor = tree_data[node_label]
        if len(neighbor.children) == 0 or visited.issuperset(neighbor.children):
            path.remove(node_label)
            visited.add(node_label)
            return self.find_path(path, tree_data, neighbor.parent, to_label, visited)
        else:
            for i in range(len(neighbor.children)):
                node = neighbor.children[i]
                if not node in visited:
                    path.append(node)
                    return self.find_path(path, tree_data, node, to_label, visited)

        return path

class Neighbor:
    def __init__(self):
        self.parent = ""
        self.children = []
