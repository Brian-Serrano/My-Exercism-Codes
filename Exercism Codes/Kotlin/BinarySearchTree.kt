class BinarySearchTree<T : Comparable<T>> {

    data class Node<T>(val data: T, var left: Node<T>? = null, var right: Node<T>? = null)

    var root: Node<T>? = null

    fun insert(value: T) {
        if (root != null) {
            insert(value, root)
        }
        else {
            root = Node(value)
        }
    }

    fun insert(value: T, node: Node<T>?) {
        if (node!!.data >= value) {
            if (node.left != null) {
                insert(value, node.left)
            }
            else {
                node.left = Node(value)
            }
        }
        else {
            if (node.right != null) {
                insert(value, node.right)
            }
            else {
                node.right = Node(value)
            }
        }
    }

    fun asSortedList(): List<T> {
        return inOrderTraversal(root, mutableListOf())
    }

    fun inOrderTraversal(node: Node<T>?, nodeData: MutableList<T>): List<T> {
        if (node != null) {
            inOrderTraversal(node.left, nodeData)
            nodeData.add(node.data)
            inOrderTraversal(node.right, nodeData)
        }
        return nodeData
    }

    fun asLevelOrderList(): List<T> {
        return breadthFirstTraversal(root, mutableListOf())
    }

    fun breadthFirstTraversal(node: Node<T>?, nodeData: MutableList<T>): List<T> {
        if (node != null) {
            val queue = mutableListOf<Node<T>>()
            queue.add(node)
            while (queue.isNotEmpty()) {
                val n = queue.removeAt(0)
                nodeData.add(n.data)
                if (n.left != null) {
                    queue.add(n.left!!)
                }
                if (n.right != null) {
                    queue.add(n.right!!)
                }
            }
        }
        return nodeData
    }
}
