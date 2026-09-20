export class Zipper {
  constructor(tree, up = null) {
    this.v = tree.value;
    this.u = up;
    this.l = tree.left ? new Zipper(tree.left, this) : null;
    this.r = tree.right ? new Zipper(tree.right, this) : null;
  }

  static fromTree(tree) {
    return new Zipper(tree);
  }

  value() {
    return this.v;
  }

  left() {
    return this.l;
  }

  right() {
    return this.r;
  }

  up() {
    if (!this.u) {
      return null;
    }

    const path = this.getPath();
    const newRoot = Zipper.fromTree(this.toTree());

    return newRoot.navigate(path.slice(0, -1));
  }

  setValue(newValue) {
    const path = this.getPath();
    const tree = this.toTree();

    let node = tree;
    for (const direction of path) {
      node = node[direction];
    }

    node.value = newValue;

    return Zipper.fromTree(tree).navigate(path);
  }

  setLeft(leftChild) {
    const path = this.getPath();
    const tree = this.toTree();

    let node = tree;
    for (const direction of path) {
      node = node[direction];
    }

    node.left = leftChild;

    return Zipper.fromTree(tree).navigate(path);
  }

  setRight(rightChild) {
    const path = this.getPath();
    const tree = this.toTree();

    let node = tree;
    for (const direction of path) {
      node = node[direction];
    }

    node.right = rightChild;

    return Zipper.fromTree(tree).navigate(path);
  }

  getPath() {
    const path = [];
    let current = this;

    while (current.u) {
      if (current.u.l === current) {
        path.push("left");
      } else {
        path.push("right");
      }

      current = current.u;
    }

    return path.reverse();
  }

  navigate(path) {
    let current = this;

    for (const direction of path) {
      current = direction === "left"
        ? current.l
        : current.r;
    }

    return current;
  }

  toTree() {
    let root = this;

    while (root.u) {
      root = root.u;
    }

    return this.generateTree(root);
  }

  generateTree(child) {
    const tree = {
      value: child.v,
      left: null,
      right: null
    };

    if (child.l) {
      tree.left = this.generateTree(child.l);
    }

    if (child.r) {
      tree.right = this.generateTree(child.r);
    }

    return tree;
  }
}