//
// This is only a SKELETON file for the 'Binary Search Tree' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class BinarySearchTree {
  constructor(value) {
    this.insert(value);
  }

  get data() {
    return this.d;
  }
  get right() {
    return this.r;
  }

  get left() {
    return this.l;
  }

  insert(value) {
    if (this.d > 0) {
      this.insrt(value, this);
    }
    else {
      this.d = value;
    }
  }

  insrt(value, node) {
    if (node.d >= value) {
      if (node.l != null) {
        this.insrt(value, node.l);
      }
      else {
        node.l = new BinarySearchTree(value);
      }
    }
    else {
      if (node.r != null) {
        this.insrt(value, node.r);
      }
      else {
        node.r = new BinarySearchTree(value);
      }
    }
  }

  each(callback) {
    return this.inOrderTraversal(this, callback);
  }

  inOrderTraversal(node, callback) {
    if (node != null) {
      this.inOrderTraversal(node.l, callback);
      callback(node.d);
      this.inOrderTraversal(node.r, callback);
    }
  }
}
