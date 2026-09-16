preorder_index = 0

def tree_from_traversals(preorder, inorder):
    inorder_input = set(inorder)
    preorder_input = set(preorder)
    if len(inorder_input) != len(inorder) or len(preorder_input) != len(preorder):
        raise ValueError("traversals must contain unique items")
    if len(preorder) != len(inorder):
        raise ValueError("traversals must have the same length")
    if inorder_input != preorder_input:
        raise ValueError("traversals must have the same elements")

    preorder_index = 0

    def build_tree(pre_order, in_order, start, end):
        nonlocal preorder_index
        if start > end:
            return {}
        n = {"v": pre_order[preorder_index], "l": {}, "r": {}}
        preorder_index += 1
        if start == end:
            return n
        inorder_index = search(in_order, start, end, n["v"])
        n["l"] = build_tree(pre_order, in_order, start, inorder_index - 1)
        n["r"] = build_tree(pre_order, in_order, inorder_index + 1, end)
        return n


    return build_tree(preorder, inorder, 0, len(inorder) - 1)

def search(inorder, start, end, n):
    i = start
    for i in range(start, end + 1):
        if inorder[i] == n:
            return i
    return i