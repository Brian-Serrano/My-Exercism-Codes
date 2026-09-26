class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def __str__(self):
        return f'TreeNode(data={self.data}, left={self.left}, right={self.right})'


class BinarySearchTree:
    def __init__(self, tree_data):
        self.root = None

        for i in range(len(tree_data)):
            self.insert(tree_data[i])

    def data(self):
        return self.root

    def sorted_data(self):
        return self.in_order_traversal(self.root, [])

    def insert(self, value):
        if self.root:
            self.insrt(value, self.root)
        else:
            self.root = TreeNode(value)

    def insrt(self, value, node):
        if self.compare(node.data, value) >= 0:
            if node.left:
                self.insrt(value, node.left)
            else:
                node.left = TreeNode(value)
        else:
            if node.right:
                self.insrt(value, node.right)
            else:
                node.right = TreeNode(value)

    def in_order_traversal(self, node, node_data):
        if node:
            self.in_order_traversal(node.left, node_data)
            node_data.append(node.data)
            self.in_order_traversal(node.right, node_data)

        return node_data

    @staticmethod
    def compare(a, b):
        return (a > b) - (a < b)