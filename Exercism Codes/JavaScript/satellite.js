//
// This is only a SKELETON file for the 'Satellite' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const treeFromTraversals = (preorder, inorder) => {
  const preOrderSet = new Set(preorder);
  const inOrderSet = new Set(inorder);
  if (preorder.length != preOrderSet.size || inorder.length != inOrderSet.size) {
    throw new Error("traversals must contain unique items");
  }
  if (preorder.length != inorder.length) {
    throw new Error("traversals must have the same length");
  }
  if (preOrderSet.symmetricDifference(inOrderSet).size !== 0) {
    throw new Error("traversals must have the same elements");
  }
  let preOrderIndex = 0;

  const buildTree = (preorder, inorder, start, end) => {
    if (start > end) {
      return {};
    }
    const n = { value: preorder[preOrderIndex++], left: {}, right: {} };
    if (start == end) {
      return n;
    }
    const inOrderIndex = search(inorder, start, end, n.value);
    n.left = buildTree(preorder, inorder, start, inOrderIndex - 1);
    n.right = buildTree(preorder, inorder, inOrderIndex + 1, end);

    return n;
  };

  return buildTree(preorder, inorder, 0, inorder.length - 1);
};

const search = (inorder, start, end, n) => {
  for (let i = start; i <= end; i++) {
    if (inorder[i] == n) {
      return i;
    }
  }
  return -1;
};
